package com.ihatebees.mixin;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.DarkScytheSwordItem;
import com.ihatebees.item.custom.StarSawSwordItem;
import com.ihatebees.sound.ModSounds;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Mixin(PlayerEntity.class)
public abstract class AttackHitboxOverwrite extends LivingEntity {
    protected AttackHitboxOverwrite(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void attack_override(CallbackInfo ci, @Local Entity target){
        boolean attack_overridden = false;

        boolean isdarkscythe = getMainHandStack().getItem() instanceof DarkScytheSwordItem item;
        boolean isstarsaw = getMainHandStack().getItem() instanceof StarSawSwordItem item;


        if (isdarkscythe || isstarsaw) {
            attack_overridden = true;
        }

        if (attack_overridden) {
            if (target.isAttackable()) {
                if (!target.handleAttack(this)) {
                    float f = (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    float g;
                    if (target instanceof LivingEntity) {
                        g = EnchantmentHelper.getAttackDamage(this.getMainHandStack(), ((LivingEntity)target).getGroup());
                    } else {
                        g = EnchantmentHelper.getAttackDamage(this.getMainHandStack(), EntityGroup.DEFAULT);
                    }

                    float h = ((PlayerEntity)(Object)this).getAttackCooldownProgress(0.5F);
                    f *= 0.2F + h * h * 0.8F;
                    g *= h;
                    ((PlayerEntity)(Object)this).resetLastAttackedTicks();
                    if (f > 0.0F || g > 0.0F) {
                        boolean bl = h > 0.9F;
                        boolean bl2 = false;
                        int i = 0;
                        i += EnchantmentHelper.getKnockback(this);
                        if (this.isSprinting() && bl) {
                            this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, this.getSoundCategory(), 1.0F, 1.0F);
                            ++i;
                            bl2 = true;
                        }

                        boolean bl3 = bl && this.fallDistance > 0.0F && !this.isOnGround() && !this.isClimbing() && !this.isTouchingWater() && !this.hasStatusEffect(StatusEffects.BLINDNESS) && !this.hasVehicle() && target instanceof LivingEntity;
                        bl3 = bl3 && !this.isSprinting();
                        if (bl3) {
                            f *= 1.5F;
                        }

                        f += g;
                        boolean bl4 = false;
                        double d = (double)(this.horizontalSpeed - this.prevHorizontalSpeed);
                        if (bl && !bl3 && !bl2 && this.isOnGround() && d < (double)this.getMovementSpeed()) {
                            ItemStack itemStack = this.getStackInHand(Hand.MAIN_HAND);
                            if (itemStack.getItem() instanceof SwordItem) {
                                bl4 = true;
                            }
                        }

                        float j = 0.0F;
                        boolean bl5 = false;
                        int k = EnchantmentHelper.getFireAspect(this);
                        if (target instanceof LivingEntity) {
                            j = ((LivingEntity)target).getHealth();
                            if (k > 0 && !target.isOnFire()) {
                                bl5 = true;
                                target.setOnFireFor(1);
                            }
                        }

                        Vec3d vec3d = target.getVelocity();
                        boolean bl6 = target.damage(this.getDamageSources().playerAttack(((PlayerEntity)(Object)this)), f);
                        if (bl6) {
                            if (i > 0) {
                                if (target instanceof LivingEntity) {
                                    ((LivingEntity)target).takeKnockback((double)((float)i * 0.5F), (double)MathHelper.sin(this.getYaw() * 0.017453292F), (double)(-MathHelper.cos(this.getYaw() * 0.017453292F)));
                                } else {
                                    target.addVelocity((double)(-MathHelper.sin(this.getYaw() * 0.017453292F) * (float)i * 0.5F), 0.1, (double)(MathHelper.cos(this.getYaw() * 0.017453292F) * (float)i * 0.5F));
                                }

                                this.setVelocity(this.getVelocity().multiply(0.6, 1.0, 0.6));
                                this.setSprinting(false);
                            }

                            if (bl4) {
                                float l = 1.0F + EnchantmentHelper.getSweepingMultiplier(this) * f;

                                // custsom hitbox logic
                                double[] hitboxsizelist;
                                double maxdistance;
                                // L = sweeping damage = 1 + sweep_level * dmg
                                // hitboxsizelist = {sizex, sizey, sizez} IN BLOCKS
                                // maxdistance in square rooted number, 49 = 7 blocks, 9 = 3 blocks

                                if (isdarkscythe) {
                                    hitboxsizelist = new double[]{5, 3.25f, 5};
                                    maxdistance = 25;
                                    l = f;
                                } else {
                                    hitboxsizelist = new double[]{1, .25, 1};
                                    maxdistance = 9;
                                }

                                // normal logic
                                List<LivingEntity> list = this.getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(hitboxsizelist[0], hitboxsizelist[1], hitboxsizelist[2]));
                                Iterator var19 = list.iterator();


                                label166:
                                while(true) {
                                    LivingEntity livingEntity;
                                    do {
                                        do {
                                            do {
                                                do {
                                                    if (!var19.hasNext()) {
                                                        if (isdarkscythe) {
                                                            this.getWorld().playSound((PlayerEntity) null, this.getX(), this.getY(), this.getZ(), ModSounds.EVENT_FLAME, this.getSoundCategory(), 1.0F, 1.0F);
                                                        } else if (isstarsaw) {
                                                            this.getWorld().playSound((PlayerEntity) null, this.getX(), this.getY(), this.getZ(), ModSounds.ITEM_SAW_SOUND, this.getSoundCategory(), 1.0F, 1.0F);
                                                        } else {
                                                            this.getWorld().playSound((PlayerEntity) null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, this.getSoundCategory(), 1.0F, 1.0F);
                                                        }
                                                        ((PlayerEntity) (Object) this).spawnSweepAttackParticles();
                                                        break label166;

                                                    }

                                                    livingEntity = (LivingEntity)var19.next();
                                                } while(livingEntity == this);
                                            } while(livingEntity == target);
                                        } while(this.isTeammate(livingEntity));
                                    } while(livingEntity instanceof ArmorStandEntity && ((ArmorStandEntity)livingEntity).isMarker());

                                    if (this.squaredDistanceTo(livingEntity) < maxdistance) {
                                        livingEntity.takeKnockback(0.4000000059604645, (double)MathHelper.sin(this.getYaw() * 0.017453292F), (double)(-MathHelper.cos(this.getYaw() * 0.017453292F)));
                                        livingEntity.damage(this.getDamageSources().playerAttack(((PlayerEntity)(Object)this)), l);
                                    }
                                }
                            }

                            if (target instanceof ServerPlayerEntity && target.velocityModified) {
                                ((ServerPlayerEntity)target).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(target));
                                target.velocityModified = false;
                                target.setVelocity(vec3d);
                            }

                            if (bl3) {
                                this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, this.getSoundCategory(), 1.0F, 1.0F);
                                ((PlayerEntity)(Object)this).addCritParticles(target);
                            }

                            if (!bl3 && !bl4) {
                                if (bl) {
                                    this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, this.getSoundCategory(), 1.0F, 1.0F);
                                } else {
                                    this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, this.getSoundCategory(), 1.0F, 1.0F);
                                }
                            }

                            if (g > 0.0F) {
                                ((PlayerEntity)(Object)this).addEnchantedHitParticles(target);
                            }

                            this.onAttacking(target);
                            if (target instanceof LivingEntity) {
                                EnchantmentHelper.onUserDamaged((LivingEntity)target, this);
                            }

                            EnchantmentHelper.onTargetDamaged(this, target);
                            ItemStack itemStack2 = this.getMainHandStack();
                            Entity entity = target;
                            if (target instanceof EnderDragonPart) {
                                entity = ((EnderDragonPart)target).owner;
                            }

                            if (!this.getWorld().isClient && !itemStack2.isEmpty() && entity instanceof LivingEntity) {
                                itemStack2.postHit((LivingEntity)entity, ((PlayerEntity)(Object)this));
                                if (itemStack2.isEmpty()) {
                                    this.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                                }
                            }

                            if (target instanceof LivingEntity) {
                                float m = j - ((LivingEntity)target).getHealth();
                                ((PlayerEntity)(Object)this).increaseStat(Stats.DAMAGE_DEALT, Math.round(m * 10.0F));
                                if (k > 0) {
                                    target.setOnFireFor(k * 4);
                                }

                                if (this.getWorld() instanceof ServerWorld && m > 2.0F) {
                                    int n = (int)((double)m * 0.5);
                                    ((ServerWorld)this.getWorld()).spawnParticles(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getBodyY(0.5), target.getZ(), n, 0.1, 0.0, 0.1, 0.2);
                                }
                            }

                            ((PlayerEntity)(Object)this).addExhaustion(0.1F);
                        } else {
                            this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, this.getSoundCategory(), 1.0F, 1.0F);
                            if (bl5) {
                                target.extinguish();
                            }
                        }
                    }

                }
            }
        }


        if (attack_overridden) {
            ci.cancel();
        }
    }
}

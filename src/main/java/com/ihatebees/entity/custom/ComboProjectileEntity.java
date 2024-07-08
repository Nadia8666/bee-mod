package com.ihatebees.entity.custom;

import com.ihatebees.BeeMod;
import com.ihatebees.entity.ModEntities;
import com.ihatebees.item.ModItems;
import com.ihatebees.particle.ModParticles;
import com.ihatebees.sound.ModSounds;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleGroup;

import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ComboProjectileEntity
        extends ThrownItemEntity {
    public int combo = 1;
    public ComboProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);

    }
    public ComboProjectileEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.COMBO_COCONUT, livingEntity, world);
    }
    @Override
    protected Item getDefaultItem() {
        return ModItems.ComboCoconut;
    }
    private ParticleEffect getParticleParameters() {
        return ModParticles.STAR_PARTICLE;
    }
    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }


    @Override
    public void tick() {
        super.tick();

        this.getWorld().addParticle(ModParticles.TRIANGLE_PARTICLE,
                this.getX()+(2*random.nextFloat()-1),
                this.getY()+(2*random.nextFloat()-1),
                this.getZ()+(2*random.nextFloat()-1),
                0,0,0);
    }
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        double dmg = Math.pow(2, 2+combo);

       // BeeMod.LOGGER.info("Combo Coconut hit!");
      //  BeeMod.LOGGER.info(String.valueOf(entity.getName()));
      //  BeeMod.LOGGER.info(String.valueOf(combo));
      //  BeeMod.LOGGER.info(String.valueOf(dmg));
      //  BeeMod.LOGGER.info("Calculations completed");
        combo+=1;
        int k = entity instanceof PlayerEntity ? 1 : 0;
        if (k==1) {
            this.getWorld().spawnEntity(new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), new ItemStack(ModItems.ComboBlessing)));
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.COMBO_INTRO, SoundCategory.NEUTRAL, 15f, 1f);
            this.discard();
        } else {
            entity.damage(this.getDamageSources().thrown(this, this.getOwner()), (float) dmg);
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {

        int Deg = 15-combo;
        for (float i = 0; ((float) 360 /Deg)>i; i++) {
            BeeMod.LOGGER.info("Attemption spawn particle");
            BeeMod.LOGGER.info(String.valueOf(i*Deg));
            this.getWorld().addParticle(this.getParticleParameters(), this.getX(), this.getY(), this.getZ(),
                    Math.cos(i*Deg) * this.random.nextInt(combo+2), this.random.nextInt(combo+2), Math.sin(i*Deg) * this.random.nextInt(combo+2));
        }

        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this,(byte)3);
        }
        this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.COMBO_END, SoundCategory.NEUTRAL, 12f, 1f);


        this.discard();
        super.onBlockHit(blockHitResult);
    }
}

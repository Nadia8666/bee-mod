package com.ihatebees.entity.custom;

import com.ihatebees.BeeMod;
import com.ihatebees.entity.ModEntities;
import com.ihatebees.item.ModItems;
import com.ihatebees.particle.ModParticles;
import com.ihatebees.sound.ModSounds;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleGroup;

import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ComboProjectileEntity extends ThrownItemEntity {
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

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
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
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), (float) dmg);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this,(byte)3);
        }
        this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.COMBO_END, SoundCategory.NEUTRAL, 12f, 1f);
        int Deg = 12;
        for (float i = 0; ((float) 360 /Deg)>i; i++) {
            BeeMod.LOGGER.info("Attemption spawn particle");
            BeeMod.LOGGER.info(String.valueOf(i*Deg));
            this.getWorld().addParticle(ModParticles.TRIANGLE_PARTICLE, this.getX(), this.getY(), this.getZ(),
                    Math.cos(i*Deg) * 20d, 20d, Math.sin(i*Deg) * 20d);
        }
        this.discard();
        super.onBlockHit(blockHitResult);
    }
}

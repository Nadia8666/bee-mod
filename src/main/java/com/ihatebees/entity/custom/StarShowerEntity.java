package com.ihatebees.entity.custom;

import com.ihatebees.entity.ModEntities;
import com.ihatebees.item.ModItems;
import com.ihatebees.particle.ModParticles;
import com.ihatebees.sound.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class StarShowerEntity
        extends ThrownItemEntity {
    public int combo = 1;

    public StarShowerEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);

    }
    public StarShowerEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.SHOWER_STAR, livingEntity, world);

    }
    @Override
    protected Item getDefaultItem() {
        return ModItems.showstar;
    }
    private ParticleEffect getParticleParameters() {
        return ModParticles.STAR_PARTICLE;
    }
    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    public ItemStack getStack() {

        return new ItemStack(ModItems.showstar);
    }
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        double dmg = 1;
        int k = entity instanceof PlayerEntity ? 1 : 0;
            entity.damage(this.getDamageSources().thrown(this, this.getOwner()), (float) dmg);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {


        this.discard();
        super.onBlockHit(blockHitResult);
    }
}

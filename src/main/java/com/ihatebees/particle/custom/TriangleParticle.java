package com.ihatebees.particle.custom;

import com.ihatebees.BeeMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;


public class TriangleParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    TriangleParticle(ClientWorld world, double x, double y, double z, double d, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider;
        this.velocityMultiplier = 0.91f;
        this.gravityStrength= 0.02f;
        this.alpha=0.2f;
        this.scale = 0.2125f;
        this.angle= this.random.nextInt(360);
        this.maxAge = 20;
        this.prevAngle = this.angle;
        int decide = this.random.nextInt(3);
        BeeMod.LOGGER.info(String.valueOf(decide));
        if (decide==2) {
            this.setColor(1,0,0);
        } else if (decide==1) {
            this.setColor(0,0,1);
        }
        else {
            this.setColor(1,1,1);
        }
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    };


    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new TriangleParticle(clientWorld, d, e, f, g, this.spriteProvider);
        }
    }
}
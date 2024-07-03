package com.ihatebees.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;


public class TriangleParticle extends SpriteBillboardParticle {
    TriangleParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.velocityMultiplier = 0.91f;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.scale = 0.005f;
        this.angle= this.random.nextInt(360);
        this.maxAge = 20*6 + this.random.nextInt(40);
        int decide = this.random.nextInt(3);
        if (decide==2) {
            this.setColor(1,0,0);
        } else if (decide==1) {
            this.setColor(0,0,1);
        }
        else {
            this.setColor(1,1,1);
        }
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
            return new StarSweepParticle(clientWorld, d, e, f, g, this.spriteProvider);
        }
    }
}
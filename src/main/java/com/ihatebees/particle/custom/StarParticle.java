package com.ihatebees.particle.custom;

import com.ihatebees.BeeMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;


public class StarParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;
    StarParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider;
        this.velocityMultiplier = 0.81f+ (float) this.random.nextInt(10)/100 ;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.gravityStrength= 1;
        this.alpha=0.2f;
        this.scale = 0.35f + (float) this.random.nextInt(20)/50;
        this.maxAge = 35 + this.random.nextInt(40);
        int decide = this.random.nextInt(3);
     //   BeeMod.LOGGER.info(String.valueOf(decide));
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
            return new StarParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
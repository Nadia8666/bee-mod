package com.ihatebees.particle;

import com.ihatebees.BeeMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType STARSWEEP_PARTICLE = FabricParticleTypes.simple(true);
    public static final DefaultParticleType TRIANGLE_PARTICLE = FabricParticleTypes.simple(true);
    public static final DefaultParticleType STAR_PARTICLE = FabricParticleTypes.simple(true);
    public static void RegisterParticles () {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(BeeMod.MODID, "starsweep_particle"),
                STARSWEEP_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(BeeMod.MODID, "triangle_particle"),
                TRIANGLE_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(BeeMod.MODID, "star_particle"),
                STAR_PARTICLE);
    }
}
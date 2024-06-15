package com.ihatebees.particle;

import com.ihatebees.BeeMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
public static final DefaultParticleType STARSWEEP = FabricParticleTypes.simple();

    public static void RegisterParticles () {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(BeeMod.MODID, "starsweep"),
                STARSWEEP);
    }
}
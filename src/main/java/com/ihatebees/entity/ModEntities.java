package com.ihatebees.entity;

import com.ihatebees.BeeMod;
import com.ihatebees.entity.custom.ComboProjectileEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.network.packet.s2c.custom.DebugBeeCustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<ComboProjectileEntity> COMBO_COCONUT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(BeeMod.MODID,"combo_coconut"),
            FabricEntityTypeBuilder.<ComboProjectileEntity>create(SpawnGroup.MISC, ComboProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(3f,3f)).build());


    public static void registerModEntities() {
        BeeMod.LOGGER.info("Registering Entities for " + BeeMod.MODID);
    }
}

package com.ihatebees.data;

import com.eliotlash.mclib.math.functions.classic.Mod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import com.ihatebees.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //   itemModelGenerator.register(ModItems.CoconutBelt, Models.GENERATED);
        itemModelGenerator.register(ModItems.CoconutCanister, Models.GENERATED);
        itemModelGenerator.register(ModItems.TestHat, Models.GENERATED);



       // itemModelGenerator.register(ModItems.StarSaw, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ScorchingStar,Models.HANDHELD);
        itemModelGenerator.register(ModItems.PopStar, Models.HANDHELD);
        //  itemModelGenerator.register(ModItems.DarkScythe, Models.HANDHELD);


        itemModelGenerator.register(ModItems.ComboCoconut,Models.GENERATED);
        itemModelGenerator.register(ModItems.ComboBlessing,Models.GENERATED);

        itemModelGenerator.register(ModItems.gummyball,Models.GENERATED);
        itemModelGenerator.register(ModItems.showstar,Models.GENERATED);
        itemModelGenerator.register(ModItems.Turpentine, Models.GENERATED);
    }
}
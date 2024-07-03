package com.ihatebees.block;

import com.ihatebees.BeeMod;
import com.ihatebees.block.custom.HiveSlotTwentyBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // blocks
    public static final Block PACKHONEY = registerBlock("packaged_honey", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    public static final Block BIG_PACKHONEY = registerBlock("big_packaged_honey", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    public static final Block HUGE_PACKHONEY = registerBlock("huge_packaged_honey", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));
    public static final Block EXTREME_PACKHONEY = registerBlock("extreme_packaged_honey", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)));


    public static final Block HIVESLOT = registerBlock("hiveslot", new BeehiveBlock(FabricBlockSettings.copyOf(Blocks.BEE_NEST)));
    public static final Block HIVESLOT_TWENTY = registerBlock("hiveslot_twenty", new HiveSlotTwentyBlock(FabricBlockSettings.copyOf(Blocks.BEE_NEST)));
    public static final Block HIVESLOT_GIFTED = registerBlock("hiveslot_gifted", new BeehiveBlock(FabricBlockSettings.copyOf(Blocks.BEE_NEST)));
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(BeeMod.MODID,name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(BeeMod.MODID,name),
                new BlockItem(block,new FabricItemSettings()));
    }

    public static void registerModBlocks() {


    }
}

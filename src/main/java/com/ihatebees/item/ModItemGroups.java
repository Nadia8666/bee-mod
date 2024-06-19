package com.ihatebees.item;

import com.ihatebees.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import com.ihatebees.BeeMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup BEE_ITEMS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BeeMod.MODID, "beeitems"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.beeitems"))
                    .icon(() -> new ItemStack(ModItems.TestThing)).entries((displayContext, entries) -> {
                        // items
                        entries.add(ModItems.TestThing);

                        // armor
                        entries.add(ModItems.CoconutBelt);
                        entries.add(ModItems.TestHat);
                        entries.add(ModItems.CoconutCanister);

                        // weapons
                        entries.add(ModItems.StarSaw);
                        entries.add(ModItems.DarkScythe);
                        entries.add(ModItems.Gummyballer);

                        // blocks
                        entries.add(ModBlocks.PACKHONEY);
                        entries.add(ModBlocks.BIG_PACKHONEY);
                        entries.add(ModBlocks.HUGE_PACKHONEY);
                        entries.add(ModBlocks.EXTREME_PACKHONEY);
                    }).build());
    public static void registerItemGroups() {
        BeeMod.LOGGER.info("Registering Item Groups for " + BeeMod.MODID);
    }
}
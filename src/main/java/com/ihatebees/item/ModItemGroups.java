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

                        // blocks
                        entries.add(ModBlocks.PACKHONEY);
                        entries.add(ModBlocks.BIG_PACKHONEY);
                        entries.add(ModBlocks.HUGE_PACKHONEY);
                        entries.add(ModBlocks.EXTREME_PACKHONEY);


                        //machine


                        // armor
                        entries.add(ModItems.TestHat);


                        entries.add(ModItems.DemonMask);
                        entries.add(ModItems.CoconutCanister);
                        entries.add(ModItems.GummyBoots);

                        entries.add(ModItems.HardHat);
                        entries.add(ModItems.Blue_Port_O_Hive);
                        entries.add(ModItems.BasicBoots);

                        entries.add(ModItems.BeekeepersHat);
                        entries.add(ModItems.Porcelain_Port_O_Hive);
                        entries.add(ModItems.BeekeepersBoots);

                        entries.add(ModItems.CoconutBelt);

                        // weapons
                        entries.add(ModItems.StarShower);
                        entries.add(ModItems.StarSaw);
                        entries.add(ModItems.DarkScythe);
                        entries.add(ModItems.Gummyballer);
                        entries.add(ModItems.TidePopper);
                        entries.add(ModItems.ComboCoconut);

                        // passive/buff
                        entries.add(ModItems.ComboBlessing);
                        entries.add(ModItems.PopStar);
                        entries.add(ModItems.GummyStar);
                        entries.add(ModItems.ScorchingStar);

                        // items

                        entries.add(ModItems.TestThing);
                        entries.add(ModItems.Turpentine);


                        // do not add gummy_ball / showstar or any other item under not real or wtf i called it
                    }).build());
    public static void registerItemGroups() {
        BeeMod.LOGGER.info("Registering Item Groups for " + BeeMod.MODID);
    }
}
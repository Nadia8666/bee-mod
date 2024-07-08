package com.ihatebees.item;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // test armor
    public static final Item TestThing = registerItem("testthing", new Item(new FabricItemSettings()));
    public static final Item TestHat = registerItem("testhat",
            new TestHatArmorItem(ModArmor.GENERIC_ARMOR, ArmorItem.Type.HELMET, new FabricItemSettings()));

    // custom armor
    public static final Item CoconutBelt = registerItem("coconutbelt",
            new CoconutBeltTrinketItem(new FabricItemSettings().maxCount(1)));
    public static final Item CoconutCanister = registerItem("coconutcanister",
            new CoconutCanisterArmorItem(ModArmor.GENERIC_ARMOR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));

    public static final Item HardHat = registerItem("hardhat",
            new BasicArmorItem(ModArmor.BASIC_ARMOR, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item Blue_Port_O_Hive = registerItem("blue_port_o_hive",
            new BasicArmorItem(ModArmor.BASIC_ARMOR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item BasicBoots = registerItem("basicboots",
            new BasicArmorItem(ModArmor.BASIC_ARMOR, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    // custom weapons
    public static final Item StarSaw = registerItem("starsaw",
            new StarSawSwordItem(ModToolMaterials.BITCH, 6, 16, new FabricItemSettings()));
    public static final Item DarkScythe = registerItem("darkscythe",
            new DarkScytheSwordItem(ModToolMaterials.ENDGAME, 11,-3.5f, new FabricItemSettings().fireproof()));
    public static final Item Gummyballer = registerItem("gummyballer",
            new GummyballerSwordItem(ModToolMaterials.ENDGAME, 8, -3, new FabricItemSettings()));
    public static final Item TidePopper = registerItem("tidepopper",
            new SwordItem(ModToolMaterials.ENDGAME, 7,-2.7f, new FabricItemSettings()));

    // custom implementations
    public static final Item GummyStar = registerItem("gummystar",
            new GummyStarPassiveItem(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item PopStar = registerItem("popstar",
            new PopStarPassiveItem(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item ScorchingStar = registerItem("scorchingstar",
            new ScorchingStarPassiveItem(new FabricItemSettings().maxCount(1).fireproof()));

    public static final Item ComboCoconut = registerItem("combococonut",
            new ComboCoconutItem(new FabricItemSettings().maxCount(40)));
    public static final Item ComboBlessing = registerItem("comboblessing",
            new Item(new FabricItemSettings().maxCount(40)));



    // dont exist
    public static final Item gummyball = registerItem("gummyball",
            new Item(new FabricItemSettings().maxCount(1)));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(TestThing);
    }


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BeeMod.MODID, name), item);
    }

    public static void registerModItems() {
        BeeMod.LOGGER.info("Registering Mod Items for " + BeeMod.MODID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}

package com.ihatebees.item;

import com.ihatebees.BeeMod;
import com.ihatebees.item.custom.armor.*;
import com.ihatebees.item.custom.buff.CBlessingItem;
import com.ihatebees.item.custom.buff.GummyStarPassiveItem;
import com.ihatebees.item.custom.buff.PopStarPassiveItem;
import com.ihatebees.item.custom.buff.ScorchingStarPassiveItem;
import com.ihatebees.item.custom.weapon.*;
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
            new TestHatArmorItem(ModArmor.MASTER_ARMOR, ArmorItem.Type.HELMET, new FabricItemSettings()));

    // custom armor
    public static final Item CoconutBelt = registerItem("coconutbelt",
            new CoconutBeltTrinketItem(new FabricItemSettings().maxCount(1)));

    public static final Item DemonMask = registerItem("demon_mask",
            new MasterArmorItem(ModArmor.MASTER_ARMOR, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item CoconutCanister = registerItem("coconutcanister",
            new MasterArmorItem(ModArmor.MASTER_ARMOR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item GummyBoots = registerItem("gummy_boots",
            new MasterArmorItem(ModArmor.MASTER_ARMOR, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item BeekeepersHat = registerItem("beekeepers_hat",
            new AdvancedArmorItem(ModArmor.ADVANCED_ARMOR, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item Porcelain_Port_O_Hive = registerItem("porcelain_port_o_hive",
            new AdvancedArmorItem(ModArmor.ADVANCED_ARMOR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item BeekeepersBoots = registerItem("beekeepers_boots",
            new AdvancedArmorItem(ModArmor.ADVANCED_ARMOR, ArmorItem.Type.BOOTS, new FabricItemSettings()));


    public static final Item HardHat = registerItem("hardhat",
            new BasicArmorItem(ModArmor.BASIC_ARMOR, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item Blue_Port_O_Hive = registerItem("blue_port_o_hive",
            new BasicArmorItem(ModArmor.BASIC_ARMOR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item BasicBoots = registerItem("basicboots",
            new BasicArmorItem(ModArmor.BASIC_ARMOR, ArmorItem.Type.BOOTS, new FabricItemSettings()));


        //// pants, other

        public static final Item BeekeepingPants = registerItem("beekeeping_pants",
            new BasicArmorItem(ModArmor.BASIC_ARMOR, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));


    // custom weapons
    public static final Item StarSaw = registerItem("starsaw",
            new StarSawSwordItem(ModToolMaterials.BITCH, 5, 16, new FabricItemSettings()));
    public static final Item DarkScythe = registerItem("darkscythe",
            new DarkScytheSwordItem(ModToolMaterials.ENDGAME, 8,-3.5f, new FabricItemSettings().fireproof()));
    public static final Item Gummyballer = registerItem("gummyballer",
            new GummyballerSwordItem(ModToolMaterials.ENDGAME, 6, -3, new FabricItemSettings()));
    public static final Item TidePopper = registerItem("tidepopper",
            new SwordItem(ModToolMaterials.ENDGAME, 7,-2.5f, new FabricItemSettings()));

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
            new CBlessingItem(new FabricItemSettings().maxCount(40)));
    public static final Item Turpentine = registerItem("turpentine",
            new MilkBucketItem(new FabricItemSettings().maxCount(25)));
    public static final Item StarShower = registerItem("starshower",
            new StarShowerItem(new FabricItemSettings().maxCount(1).maxDamage(41)));


    // dont exist
    public static final Item gummyball = registerItem("gummyball",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item showstar = registerItem("showstar",
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

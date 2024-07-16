package com.ihatebees.item;

import com.ihatebees.BeeMod;
import com.ihatebees.block.ModBlocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
// i stole this :)

import java.util.function.Supplier;

public enum ModArmor implements ArmorMaterial {


    MASTER_ARMOR("master_armor", 35, new int[] { 0, 0, 0, 0 }, 30,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0f, 0f, () -> Ingredient.ofItems(ModBlocks.EXTREME_PACKHONEY)),
    BASIC_ARMOR("basic_armor", 15, new int[] { 0, 0, 2, 0 }, 4,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0f, 0f, () -> Ingredient.ofItems(ModBlocks.BIG_PACKHONEY)),
    ADVANCED_ARMOR("advanced_armor", 25, new int[] { 0, 0, 0, 0 }, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0f, 0f, () -> Ingredient.ofItems(ModBlocks.HUGE_PACKHONEY)),

    ;



    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 11, 16, 15, 13 };

    ModArmor(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound,
             float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return BeeMod.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
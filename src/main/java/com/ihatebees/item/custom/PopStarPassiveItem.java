package com.ihatebees.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.ihatebees.sound.ModSounds;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.UUID;

public class PopStarPassiveItem extends Item {
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public PopStarPassiveItem(Settings settings) {
        super(settings);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(UUID.randomUUID(), "Armor toughness", 1, EntityAttributeModifier.Operation.ADDITION));

        this.attributeModifiers = builder.build();
    }


    public int getMaxUseTime(ItemStack stack) {
        return 60*3;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20*10, 0));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1,0));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 20*45, 1));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 20*45, 2));
        user.getWorld().playSound((PlayerEntity) user, user.getX(),user.getY(),user.getZ(), ModSounds.ITEM_POPSTAR, SoundCategory.RECORDS, 0.3f, 1f);
        user.getItemCooldownManager().set(this,20*60*3);
        return super.use(world, user, hand);
    }
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.OFFHAND) {
            return this.attributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }
}

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


public class GummyStarPassiveItem extends Item {
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public GummyStarPassiveItem(Settings settings) {
        super(settings);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(UUID.randomUUID(), "Armor knockback resistance", .2, EntityAttributeModifier.Operation.ADDITION));

        this.attributeModifiers = builder.build();
    }

    @Override


    public int getMaxUseTime(ItemStack stack) {
        return 60*3;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20*2, 0));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20*45, 3));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*45, 3));
        user.getWorld().playSound((PlayerEntity) user, user.getX(),user.getY(),user.getZ(), ModSounds.ITEM_GUMMYSTAR, SoundCategory.RECORDS, 0.3f, 1f);
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

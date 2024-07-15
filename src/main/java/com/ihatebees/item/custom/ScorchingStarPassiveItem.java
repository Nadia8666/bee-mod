package com.ihatebees.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.ihatebees.sound.ModSounds;
import net.minecraft.enchantment.Enchantment;
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
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.UUID;

public class ScorchingStarPassiveItem extends Item {
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;


    public ScorchingStarPassiveItem(Settings settings) {
        super(settings);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(UUID.randomUUID(), "Weapon modifier", .15, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

        this.attributeModifiers = builder.build();
    }


    public int getMaxUseTime(ItemStack stack) {
        return 60*3;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20*5, 0));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1,0));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20*45, 0));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 20*45, 0));
        user.getWorld().playSound((PlayerEntity) user, user.getX(),user.getY(),user.getZ(), ModSounds.ITEM_SCORCHINGSTAR, SoundCategory.RECORDS, 0.3f, 1f);
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

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


public class CBlessingItem extends Item {

    public CBlessingItem(Settings settings) {
        super(settings);
    }

    public int getMaxUseTime(ItemStack stack) {
        return 60;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 0));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 20*60, 0));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20*60, 0));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*60, 0));
        user.getWorld().playSound((PlayerEntity) user, user.getX(),user.getY(),user.getZ(), ModSounds.EVENT_ITEM, SoundCategory.PLAYERS, 0.7f, 1f);
        user.getItemCooldownManager().set(this,20);
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return super.use(world, user, hand);
    }
}

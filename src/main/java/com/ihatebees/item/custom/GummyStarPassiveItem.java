package com.ihatebees.item.custom;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.ihatebees.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FireworkStarItem;
import net.minecraft.item.Instrument;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class GummyStarPassiveItem extends Item {
    public GummyStarPassiveItem(Settings settings) {
        super(settings);
    }


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
}

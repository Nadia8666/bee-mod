package com.ihatebees.item.custom;

import com.ihatebees.entity.custom.ComboProjectileEntity;
import com.ihatebees.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SnowballItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ComboCoconutItem extends Item {
    public ComboCoconutItem(Settings settings) {
        super(settings);
    }





    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.COMBO_START, SoundCategory.NEUTRAL, 0.5f, 1f);
        user.getItemCooldownManager().set(this,20);
        if (!world.isClient) {
            ComboProjectileEntity comboProjectileEntity = new ComboProjectileEntity(user, world);
            comboProjectileEntity.setItem(itemStack);
            comboProjectileEntity.setVelocity(user,  -(90*((float) 1 /4)) + user.getPitch()*((float) 3/4), user.getYaw(), 0.0f, 0.65f, 0.2f);
            world.spawnEntity(comboProjectileEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}



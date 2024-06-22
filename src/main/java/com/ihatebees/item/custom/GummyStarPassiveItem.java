package com.ihatebees.item.custom;

import com.ihatebees.sound.ModSounds;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


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

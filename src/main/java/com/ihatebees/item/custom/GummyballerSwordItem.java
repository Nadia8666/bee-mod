package com.ihatebees.item.custom;

import com.ihatebees.entity.custom.ComboProjectileEntity;
import com.ihatebees.entity.custom.GummyballEntity;
import com.ihatebees.sound.ModSounds;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GummyballerSwordItem
        extends SwordItem {
    public GummyballerSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.GUMMY_START, SoundCategory.NEUTRAL, 0.5f, 1f);
        user.getItemCooldownManager().set(this,20);
        if (!world.isClient) {
            GummyballEntity gummyballentity = new GummyballEntity(user, world);
            gummyballentity.setItem(itemStack);
            gummyballentity.setVelocity(user,  user.getPitch(), user.getYaw(), 0.0f, 1f, 0f);
            world.spawnEntity(gummyballentity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.damage(2, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}

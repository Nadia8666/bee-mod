package com.ihatebees.item.custom;

import com.ihatebees.item.client.CoconutCanisterRenderer;
import com.ihatebees.sound.ModSounds;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundExecutor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class StarSawSwordItem extends SwordItem {
    public StarSawSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.getWorld().playSound((PlayerEntity) null, attacker.getX(),attacker.getY(),attacker.getZ(), ModSounds.ITEM_SAW_SOUND, SoundCategory.BLOCKS, 1.0F, 1.0F);
       return super.postHit(stack,target,attacker);
    }
}
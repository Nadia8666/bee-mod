package com.ihatebees.item.custom;

import com.ihatebees.entity.custom.ComboProjectileEntity;

import com.ihatebees.entity.custom.StarShowerEntity;
import com.ihatebees.item.client.StarSawRenderer;
import com.ihatebees.item.client.StarShowerRenderer;
import com.ihatebees.sound.ModSounds;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class StarShowerItem extends Item implements GeoItem {
    public StarShowerItem(Settings settings) {
        super(settings);
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }
    public static boolean isUsable(ItemStack stack) {
        return stack.getDamage() < stack.getMaxDamage() - 1;
    }
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }
    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.min(Math.round(13.0f - ((float)stack.getDamage() )* 13.0f / ( (float)this.getMaxDamage() -1 ) ),13);
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        float f = Math.max(0.0f, ((float)this.getMaxDamage() - (float)stack.getDamage()) / (float)this.getMaxDamage());
        return MathHelper.hsvToRgb(1 / 6.0f, .84f, .94f);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (isUsable(user.getStackInHand(hand))) {
            ItemStack itemStack = user.getStackInHand(hand);
            world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.EVENT_SHOWER, SoundCategory.NEUTRAL, 0.5f, 1f);
            user.getItemCooldownManager().set(this, 4);
            if (!world.isClient) {
                StarShowerEntity comboProjectileEntity = new StarShowerEntity(user, world);
                comboProjectileEntity.setItem(itemStack);
                comboProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.35f, 14f);
                world.spawnEntity(comboProjectileEntity);
            }
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.getAbilities().creativeMode) {
                itemStack.damage(1, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            }
            return TypedActionResult.success(itemStack, world.isClient());
        } else {

            return TypedActionResult.fail(user.getStackInHand(hand));
        }
    }




    //geo

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);


    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private final StarShowerRenderer renderer = new StarShowerRenderer();

            @Override
            public BuiltinModelItemRenderer getCustomRenderer() {
                return this.renderer;
            }
        });
    }


    @Override
    public Supplier<Object> getRenderProvider() {
        return this.renderProvider;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller",20, this::predicate ));
    }

    private PlayState predicate(AnimationState<StarShowerItem> starShowerItemAnimationState) {
        starShowerItemAnimationState.getController().setAnimation(RawAnimation.begin().then("Idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}



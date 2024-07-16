package com.ihatebees.item.custom;

import com.ihatebees.item.client.tool.DarkScytheRenderer;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DarkScytheSwordItem extends SwordItem implements GeoItem {
    public DarkScytheSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

 //   @Override
   // public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
  //      attacker.getWorld().playSound((PlayerEntity) null, attacker.getX(),attacker.getY(),attacker.getZ(), ModSounds.ITEM_SAW_SOUND, SoundCategory.BLOCKS, 1.0F, 1.0F);
  //     return super.postHit(stack,target,attacker);
  //  }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private final DarkScytheRenderer renderer = new DarkScytheRenderer();

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

    private PlayState predicate(AnimationState<DarkScytheSwordItem> darkScytheSwordItemAnimationState) {
        darkScytheSwordItemAnimationState.getController().setAnimation(RawAnimation.begin().then("Idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
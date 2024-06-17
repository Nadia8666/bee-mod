package com.ihatebees.mixin;

import com.ihatebees.item.custom.DarkScytheSwordItem;
import com.ihatebees.particle.ModParticles;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class HitEffectMixin extends LivingEntity {
	protected HitEffectMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(at = @At("HEAD"), method = "spawnSweepAttackParticles", cancellable = true)
	private void onSweep(CallbackInfo cir) {
		boolean did_trigger = false;
		if (getMainHandStack().getItem() instanceof DarkScytheSwordItem item) {
			double d = (double) (-MathHelper.sin(this.getYaw() * 0.017453292F));
			double e = (double) MathHelper.cos(this.getYaw() * 0.017453292F);
			if (this.getWorld() instanceof ServerWorld) {
				((ServerWorld) this.getWorld()).spawnParticles(ModParticles.STARSWEEP_PARTICLE, this.getX() + d, this.getBodyY(0.5), this.getZ() + e, 0, d, 0.0, e, 0.0);
			}

			did_trigger = true;
		}

		// if any of the items activated their custom effects, stop the default effect from happening
		if (did_trigger) {
			cir.cancel();
		}
	}
}

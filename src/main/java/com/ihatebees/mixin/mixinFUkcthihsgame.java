package com.ihatebees.mixin;

import com.ihatebees.item.custom.StarSawSwordItem;
import com.ihatebees.particle.ModParticles;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class mixinFUkcthihsgame extends LivingEntity {
	protected mixinFUkcthihsgame(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(at = @At("HEAD"), method = "spawnSweepAttackParticles")
		private void onSweep(CallbackInfo cir) {
			if (getMainHandStack().getItem() instanceof StarSawSwordItem item) {
				double d = (double) (-MathHelper.sin(this.getYaw() * 0.017453292F));
				double e = (double) MathHelper.cos(this.getYaw() * 0.017453292F);
				if (this.getEntityWorld() instanceof ServerWorld) {
					((ServerWorld) this.getEntityWorld()).spawnParticles(ModParticles.STARSWEEP, this.getX() + d, this.getBodyY(0.5), this.getZ() + e, 0, d, 0.0, e, 0.0);
				}
			}
		}
}
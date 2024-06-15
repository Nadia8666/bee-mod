package com.ihatebees;

import com.ihatebees.item.ModItems;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.util.math.RotationAxis;

public class BeeModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering
		TrinketRendererRegistry.registerRenderer(ModItems.CoconutBelt,
				(stack, slotReference, contextModel, matrices, vertexConsumers, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch) -> {
					net.minecraft.world.World world = MinecraftClient.getInstance().world;
					if (entity instanceof AbstractClientPlayerEntity player) {
						TrinketRenderer.translateToChest(matrices, (PlayerEntityModel<AbstractClientPlayerEntity>) contextModel, player);

						// tweak position rotation, remove item frame scaling
						matrices.translate(0, .25, .1);
						matrices.scale(1.3F, 1.3F, 1.3F);

						// rotate 180 horizontally around the character and 180 vertically around
						matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
						matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));

						MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.FIXED, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, world, 0);
					}
				});
	}
}
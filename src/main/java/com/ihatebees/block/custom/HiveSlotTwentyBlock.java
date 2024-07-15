package com.ihatebees.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Iterator;
import java.util.List;

public class HiveSlotTwentyBlock extends BeehiveBlock {
    public static final MapCodec<HiveSlotTwentyBlock> CODEC = createCodec(HiveSlotTwentyBlock::new);
    public static final DirectionProperty FACING;
    public static final IntProperty HONEY_LEVEL;
    public static final int FULL_HONEY_LEVEL = 4;
    private static final int DROPPED_HONEYCOMB_COUNT = 3;
    public HiveSlotTwentyBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(HONEY_LEVEL, 0)).with(FACING, Direction.NORTH));
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        int i = (Integer)state.get(HONEY_LEVEL);
        boolean bl = false;
        if (i >= 4) {
            Item item = itemStack.getItem();
            if (itemStack.isOf(Items.SHEARS)) {
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
                dropHoneycomb(world, pos);
                itemStack.damage(1, player, (playerx) -> {
                    playerx.sendToolBreakStatus(hand);
                });
                bl = true;
                world.emitGameEvent(player, GameEvent.SHEAR, pos);
            } else if (itemStack.isOf(Items.GLASS_BOTTLE)) {
                itemStack.decrement(1);
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (itemStack.isEmpty()) {
                    player.setStackInHand(hand, new ItemStack(Items.HONEY_BOTTLE));
                } else if (!player.getInventory().insertStack(new ItemStack(Items.HONEY_BOTTLE))) {
                    player.dropItem(new ItemStack(Items.HONEY_BOTTLE), false);
                }

                bl = true;
                world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);
            }

            if (!world.isClient() && bl) {
                player.incrementStat(Stats.USED.getOrCreateStat(item));
            }
        }

        if (bl) {
            if (!CampfireBlock.isLitCampfireInRange(world, pos)) {
                if (this.hasBees(world, pos)) {
                    this.angerNearbyBees(world, pos);
                }

                this.takeHoney(world, state, pos, player, BeehiveBlockEntity.BeeState.EMERGENCY);
            } else {
                this.takeHoney(world, state, pos);
            }

            return ActionResult.success(world.isClient);
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }


    private boolean hasBees(World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BeehiveBlockEntity beehiveBlockEntity) {
            return !beehiveBlockEntity.hasNoBees();
        } else {
            return false;
        }
    }
    private void angerNearbyBees(World world, BlockPos pos) {
        Box box = (new Box(pos)).expand(8.0, 6.0, 8.0);
        List<BeeEntity> list = world.getNonSpectatingEntities(BeeEntity.class, box);
        if (!list.isEmpty()) {
            List<PlayerEntity> list2 = world.getNonSpectatingEntities(PlayerEntity.class, box);
            if (list2.isEmpty()) {
                return;
            }

            Iterator var6 = list.iterator();

            while(var6.hasNext()) {
                BeeEntity beeEntity = (BeeEntity)var6.next();
                if (beeEntity.getTarget() == null) {
                    PlayerEntity playerEntity = (PlayerEntity) Util.getRandom(list2, world.random);
                    beeEntity.setTarget(playerEntity);
                }
            }
        }

    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{HONEY_LEVEL, FACING});
    }
    static {
        FACING = HorizontalFacingBlock.FACING;
        HONEY_LEVEL = Properties.HONEY_LEVEL;
    }
}

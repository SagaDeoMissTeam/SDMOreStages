package net.sixik.sdmorestages.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.sixik.sdmorestages.SDMOreStages;
import net.sixik.sdmorestages.common.BlockStageInfo;
import net.sixik.sdmorestages.common.OreStageContainer;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OreBlockHelper {


    private static Player currentPlayer;
    private static final List<ItemStack> EMPTY_DROPS = new ArrayList();

    public OreBlockHelper() {
    }

    public static void setPlayer(@Nullable Player player) {
        currentPlayer = player;
    }

    @Nullable
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static BlockState getBlockState(BlockPos blockPos, Level level) {
        return level.getBlockState(blockPos);
    }

    public static Block getBlock(BlockState blockState) {
        return blockState.getBlock();
    }

    public static boolean isBlock(Block block) {
        return block != null;
    }

    public static boolean isBlock(BlockState blockState) {
        return blockState != null;
    }

    public static boolean isReplacedBlock(BlockState a, Block b) {
        return isBlock(a) && isBlock(b) && !a.is(b);
    }

    public static boolean isReplacedBlock(BlockState a, BlockState b) {
        return isBlock(a) && isBlock(b) && !b.is(a.getBlock());
    }

    public static ResourceLocation getBlockName(Block block) {
        return block.arch$registryName();
    }

    public static ResourceLocation getBlockName(BlockState blockState) {
        return getBlockName(getBlock(blockState));
    }

    public static BlockState getReplacement(Player player, BlockState original, BlockPos pos) {
        BlockState replace = null;

        for (BlockStageInfo info : OreStageContainer.GLOBAL_BLOCK_INFO) {
            if (info.getBlock() != null) {
                if ((info.getBlock().equals(original) || original.getBlock().defaultBlockState().equals(info.getBlock())) && !SDMOreStages.STAGES.hasStage(player, info.getStage())) {
                    replace = info.getBlockReplace();
                    return replace;
                }
            } else if (info.getTag().contains(original.getBlock()) && !SDMOreStages.STAGES.hasStage(player, info.getStage())) {
                replace = info.getBlockReplace();
                return replace;
            }
        }

        return original;
    }

    public static int getReplacementId(BlockState original, @Nullable BlockPos pos) {
        BlockState replacement = original;
        if (currentPlayer != null) {
            BlockPos actualPos = pos == null ? currentPlayer.blockPosition() : pos;
            BlockState maybeReplacement = getReplacement(currentPlayer, original, actualPos);
            if (isReplacedBlock(original, maybeReplacement)) {
                replacement = maybeReplacement;
            }
        }

        return Block.BLOCK_STATE_REGISTRY.getId(replacement);
    }

    public static List<ItemStack> getDrops(Player player, BlockState original, ServerLevel serverLevel, BlockPos blockPos, @Nullable BlockEntity blockEntity, ItemStack tool) {
        BlockState replacement = getReplacement(player, original, blockPos);
        if (isReplacedBlock(original, replacement)) {
            List<ItemStack> drops = Block.getDrops(replacement, serverLevel, blockPos, blockEntity, player, tool);
            return drops;
        } else {
            return EMPTY_DROPS;
        }
    }
}

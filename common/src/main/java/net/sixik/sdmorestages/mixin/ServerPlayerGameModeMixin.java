package net.sixik.sdmorestages.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.sixik.sdmorestages.utils.OreBlockHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ServerPlayerGameMode.class})
public class ServerPlayerGameModeMixin {
    @Shadow
    @Final
    protected ServerPlayer player;

    public ServerPlayerGameModeMixin() {
    }

    private BlockState replaceBlock(BlockState original, BlockPos blockPos, String logAction) {
        return OreBlockHelper.getReplacement(this.player, original, blockPos);
    }

    @Redirect(
            method = {"handleBlockBreakAction"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"
            )
    )
    public BlockState onBlockBreak(ServerLevel instance, BlockPos blockPos) {
        return this.replaceBlock(instance.getBlockState(blockPos), blockPos, "break");
    }

    @Redirect(
            method = {"destroyBlock"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"
            )
    )
    public BlockState onDestroyBlock(ServerLevel instance, BlockPos blockPos) {
        return this.replaceBlock(instance.getBlockState(blockPos), blockPos, "destroying");
    }

    @Redirect(
            method = {"useItemOn"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"
            )
    )
    public BlockState onUseItemOn(Level instance, BlockPos blockPos) {
        return this.replaceBlock(instance.getBlockState(blockPos), blockPos, "right clicking");
    }
}

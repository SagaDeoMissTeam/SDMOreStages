package net.sixik.sdmorestages.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.sixik.sdmorestages.utils.OreBlockHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class MixinPlayer {

    @Shadow
    public abstract boolean hasCorrectToolForDrops(BlockState arg);

    @Inject(method = "hasCorrectToolForDrops", at = @At("HEAD"), cancellable = true)
    public void onHasCorrectToolForDrops(BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        var replacement = OreBlockHelper.getReplacement((Player) (Object) this, blockState, ((Player) (Object) this).blockPosition());
        if (OreBlockHelper.isReplacedBlock(blockState, replacement)) {
            cir.setReturnValue(this.hasCorrectToolForDrops(replacement));
        }
    }
}

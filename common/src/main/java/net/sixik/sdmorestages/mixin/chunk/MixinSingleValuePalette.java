package net.sixik.sdmorestages.mixin.chunk;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.SingleValuePalette;
import net.sixik.sdmorestages.utils.OreBlockHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({SingleValuePalette.class})
public class MixinSingleValuePalette<T> {
    @Shadow
    @Nullable
    private T value;

    public MixinSingleValuePalette() {
    }

    @Inject(
            method = {"write"},
            at = {@At("HEAD")},
            cancellable = true
    )
    public void onWrite(FriendlyByteBuf arg, CallbackInfo ci) {
        if (this.value != null && this.value instanceof BlockState) {
            arg.writeVarInt(OreBlockHelper.getReplacementId((BlockState)this.value, (BlockPos)null));
            ci.cancel();
        }

    }
}


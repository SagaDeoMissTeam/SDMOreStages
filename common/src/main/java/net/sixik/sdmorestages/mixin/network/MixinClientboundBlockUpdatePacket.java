package net.sixik.sdmorestages.mixin.network;


import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockState;
import net.sixik.sdmorestages.api.IInterceptedClientboundPacket;
import net.sixik.sdmorestages.utils.OreBlockHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ClientboundBlockUpdatePacket.class})
public class MixinClientboundBlockUpdatePacket implements IInterceptedClientboundPacket {
    @Shadow
    @Final
    @Mutable
    private BlockState blockState;
    @Shadow
    @Final
    private BlockPos pos;

    public MixinClientboundBlockUpdatePacket() {
    }

    public void interceptRestrictions(ServerPlayer player) {
        BlockState newState = OreBlockHelper.getReplacement(player, this.blockState, this.pos);
        if (OreBlockHelper.isReplacedBlock(this.blockState, newState)) {
            this.blockState = newState;
        }

    }
}


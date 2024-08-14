package net.sixik.sdmorestages.mixin.network;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.network.protocol.game.ClientboundSectionBlocksUpdatePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockState;
import net.sixik.sdmorestages.api.IInterceptedClientboundPacket;
import net.sixik.sdmorestages.utils.OreBlockHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ClientboundSectionBlocksUpdatePacket.class})
public class MixinClientboundSectionBlocksUpdatePacket implements IInterceptedClientboundPacket {
    @Shadow
    @Final
    private BlockState[] states;
    @Shadow
    @Final
    private SectionPos sectionPos;
    @Shadow
    @Final
    private short[] positions;

    public MixinClientboundSectionBlocksUpdatePacket() {
    }

    public void interceptRestrictions(ServerPlayer player) {
        for(int i = 0; i < this.states.length; ++i) {
            short posOffset = this.positions[i];
            BlockPos blockPos = new BlockPos(this.sectionPos.relativeToBlockX(posOffset), this.sectionPos.relativeToBlockY(posOffset), this.sectionPos.relativeToBlockZ(posOffset));
            BlockState newState = OreBlockHelper.getReplacement(player, this.states[i], blockPos);
            if (OreBlockHelper.isReplacedBlock(this.states[i], newState)) {
                this.states[i] = newState;
            }
        }

    }
}

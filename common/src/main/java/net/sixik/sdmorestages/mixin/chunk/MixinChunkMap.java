package net.sixik.sdmorestages.mixin.chunk;

import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.chunk.LevelChunk;
import net.sixik.sdmorestages.utils.OreBlockHelper;
import org.apache.commons.lang3.mutable.MutableObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ChunkMap.class})
public class MixinChunkMap {
    public MixinChunkMap() {
    }

    @Inject(
            method = {"playerLoadedChunk"},
            at = {@At("HEAD")}
    )
    public void beforePlayerLoadedChunk(ServerPlayer serverPlayer, MutableObject<ClientboundLevelChunkWithLightPacket> mutableObject, LevelChunk levelChunk, CallbackInfo ci) {
        OreBlockHelper.setPlayer(serverPlayer);
    }

    @Inject(
            method = {"playerLoadedChunk"},
            at = {@At("TAIL")}
    )
    public void afterPlayerLoadedChunk(ServerPlayer serverPlayer, MutableObject<ClientboundLevelChunkWithLightPacket> mutableObject, LevelChunk levelChunk, CallbackInfo ci) {
        OreBlockHelper.setPlayer((Player)null);
    }
}
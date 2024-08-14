package net.sixik.sdmorestages.mixin;

import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChunkMap.class)
public interface ChunkMapAccessor {

    @Invoker("updatePlayerStatus")
    void setUpdatePlayerStatus(ServerPlayer p_140193_, boolean p_140194_);
}

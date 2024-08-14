package net.sixik.sdmorestages.api;

import net.minecraft.server.level.ServerPlayer;

public interface IInterceptedClientboundPacket {
    void interceptRestrictions(ServerPlayer var1);
}

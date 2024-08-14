package net.sixik.sdmorestages.mixin.network;

import net.minecraft.network.PacketSendListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.sixik.sdmorestages.api.IInterceptedClientboundPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ServerGamePacketListenerImpl.class})
public class MixinServerGamePacketListenerImpl {
    @Shadow
    public ServerPlayer player;

    public MixinServerGamePacketListenerImpl() {
    }

    @Inject(
            method = {"send(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketSendListener;)V"},
            at = {@At("HEAD")}
    )
    public void onSend(Packet<?> packet, PacketSendListener packetSendListener, CallbackInfo ci) {
        if (packet instanceof IInterceptedClientboundPacket restrictedPacket) {
            restrictedPacket.interceptRestrictions(this.player);
        }

    }
}
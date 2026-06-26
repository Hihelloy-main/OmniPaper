package io.github.omnipaper.server.packet;

import io.github.omnipaper.api.packet.PacketAPI;
import io.netty.channel.Channel;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OmniPacketAPIImpl implements PacketAPI {

    private Channel getChannel(Player player) {
        ServerPlayer handle = ((CraftPlayer) player).getHandle();
        return handle.connection.connection.channel;
    }

    @Override
    public void sendPacket(@NotNull Player player, @NotNull Object packet) {
        if (!(packet instanceof Packet<?> nmsPacket)) {
            throw new IllegalArgumentException("packet must be a net.minecraft.network.protocol.Packet");
        }
        ServerPlayer handle = ((CraftPlayer) player).getHandle();
        handle.connection.send(nmsPacket);
    }

    @Override
    public void receivePacket(@NotNull Player player, @NotNull Object packet) {
        if (!(packet instanceof Packet<?>)) {
            throw new IllegalArgumentException("packet must be a net.minecraft.network.protocol.Packet");
        }
        Channel channel = this.getChannel(player);
        channel.pipeline().context(OmniPacketHandler.HANDLER_NAME).fireChannelRead(packet);
    }

    @Override
    public boolean isInjected(@NotNull Player player) {
        Channel channel = this.getChannel(player);
        return channel.pipeline().get(OmniPacketHandler.HANDLER_NAME) != null;
    }

    @Override
    public void inject(@NotNull Player player) {
        Channel channel = this.getChannel(player);
        if (channel.pipeline().get(OmniPacketHandler.HANDLER_NAME) != null) {
            return;
        }
        channel.eventLoop().submit(() -> {
            channel.pipeline().addBefore("packet_handler", OmniPacketHandler.HANDLER_NAME, new OmniPacketHandler(player));
        });
    }

    @Override
    public void uninject(@NotNull Player player) {
        Channel channel = this.getChannel(player);
        if (channel.pipeline().get(OmniPacketHandler.HANDLER_NAME) == null) {
            return;
        }
        channel.eventLoop().submit(() -> {
            if (channel.pipeline().get(OmniPacketHandler.HANDLER_NAME) != null) {
                channel.pipeline().remove(OmniPacketHandler.HANDLER_NAME);
            }
        });
    }
}
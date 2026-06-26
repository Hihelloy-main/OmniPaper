package io.github.omnipaper.server.packet;

import io.github.omnipaper.api.packet.PacketDirection;
import io.github.omnipaper.api.packet.PacketEvent;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OmniPacketHandler extends ChannelDuplexHandler {

    public static final String HANDLER_NAME = "omnipaper_packet_handler";

    private final Player player;

    public OmniPacketHandler(Player player) {
        this.player = player;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof net.minecraft.network.protocol.Packet) {
            PacketEvent event = new PacketEvent(msg, PacketDirection.INBOUND, this.player);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
            super.channelRead(ctx, event.getPacket());
            return;
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof net.minecraft.network.protocol.Packet) {
            PacketEvent event = new PacketEvent(msg, PacketDirection.OUTBOUND, this.player);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
            super.write(ctx, event.getPacket(), promise);
            return;
        }
        super.write(ctx, msg, promise);
    }
}
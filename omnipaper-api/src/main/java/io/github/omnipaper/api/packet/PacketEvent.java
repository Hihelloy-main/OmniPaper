package io.github.omnipaper.api.packet;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PacketEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Object packet;
    private final PacketDirection direction;
    private final Player player;
    private boolean cancelled;

    public PacketEvent(@NotNull Object packet, @NotNull PacketDirection direction, @Nullable Player player) {
        super(true);
        this.packet = packet;
        this.direction = direction;
        this.player = player;
    }

    @NotNull
    public Object getPacket() {
        return this.packet;
    }

    @NotNull
    public String getPacketName() {
        return this.packet.getClass().getSimpleName();
    }

    @NotNull
    public PacketDirection getDirection() {
        return this.direction;
    }

    @Nullable
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
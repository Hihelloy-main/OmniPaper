package io.github.omnipaper.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Fired at the end of each server tick with basic tick timing data.
 */
public final class ServerTickEndEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final long tickNumber;
    private final double tickDurationMs;
    private final double tps1s;
    private final double tps1min;

    public ServerTickEndEvent(long tickNumber, double tickDurationMs, double tps1s, double tps1min) {
        super(true);
        this.tickNumber = tickNumber;
        this.tickDurationMs = tickDurationMs;
        this.tps1s = tps1s;
        this.tps1min = tps1min;
    }

    public long getTickNumber() {
        return tickNumber;
    }

    public double getTickDurationMs() {
        return tickDurationMs;
    }

    public boolean isLagging() {
        return tickDurationMs > 50.0;
    }

    public double getTps1s() {
        return tps1s;
    }

    public double getTps1min() {
        return tps1min;
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

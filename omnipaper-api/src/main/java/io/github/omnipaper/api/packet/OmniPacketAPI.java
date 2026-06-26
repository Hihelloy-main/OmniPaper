package io.github.omnipaper.api.packet;

import org.jetbrains.annotations.NotNull;

public final class OmniPacketAPI {

    private static PacketAPI instance;

    private OmniPacketAPI() {
    }

    @NotNull
    public static PacketAPI get() {
        if (instance == null) {
            throw new IllegalStateException("PacketAPI has not been initialized yet");
        }
        return instance;
    }

    public static void setInstance(@NotNull PacketAPI api) {
        if (instance != null) {
            throw new IllegalStateException("PacketAPI is already initialized");
        }
        instance = api;
    }
}
package io.github.omnipaper.api.packet;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface PacketAPI {

    void sendPacket(@NotNull Player player, @NotNull Object packet);

    void receivePacket(@NotNull Player player, @NotNull Object packet);

    boolean isInjected(@NotNull Player player);

    void inject(@NotNull Player player);

    void uninject(@NotNull Player player);
}
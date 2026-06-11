package io.github.omnipaper.server.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.jspecify.annotations.NullMarked;

import java.io.File;
import java.io.IOException;

@NullMarked
public final class OmniPaperGlobalConfig {

    public final Performance performance;
    public final Async async;
    public final Network network;
    public final Misc misc;

    private OmniPaperGlobalConfig(YamlConfiguration cfg) {
        this.performance = new Performance(cfg);
        this.async = new Async(cfg);
        this.network = new Network(cfg);
        this.misc = new Misc(cfg);
    }

    public static OmniPaperGlobalConfig load(File file) throws Exception {
        YamlConfiguration cfg = new YamlConfiguration();
        if (file.exists()) {
            cfg.load(file);
        }
        OmniPaperGlobalConfig config = new OmniPaperGlobalConfig(cfg);
        cfg.save(file);
        return config;
    }

    public static final class Performance {
        public final boolean enableAdaptiveTick;
        public final double adaptiveTickThresholdMs;
        public final double adaptiveTickRecoveryMs;
        public final double adaptiveTickFloorTps;
        public final int entityCullIntervalTicks;
        public final boolean entityCullEnabled;

        Performance(YamlConfiguration cfg) {
            enableAdaptiveTick = cfg.getBoolean("performance.adaptive-tick.enabled", true);
            adaptiveTickThresholdMs = cfg.getDouble("performance.adaptive-tick.threshold-ms", 55.0);
            adaptiveTickRecoveryMs = cfg.getDouble("performance.adaptive-tick.recovery-ms", 45.0);
            adaptiveTickFloorTps = cfg.getDouble("performance.adaptive-tick.floor-tps", 15.0);
            entityCullEnabled = cfg.getBoolean("performance.entity-cull.enabled", true);
            entityCullIntervalTicks = cfg.getInt("performance.entity-cull.interval-ticks", 200);
            set(cfg);
        }

        private void set(YamlConfiguration cfg) {
            cfg.set("performance.adaptive-tick.enabled", enableAdaptiveTick);
            cfg.set("performance.adaptive-tick.threshold-ms", adaptiveTickThresholdMs);
            cfg.set("performance.adaptive-tick.recovery-ms", adaptiveTickRecoveryMs);
            cfg.set("performance.adaptive-tick.floor-tps", adaptiveTickFloorTps);
            cfg.set("performance.entity-cull.enabled", entityCullEnabled);
            cfg.set("performance.entity-cull.interval-ticks", entityCullIntervalTicks);
        }
    }

    public static final class Async {
        public final boolean asyncPathfindingEnabled;
        public final int asyncPathfindingMaxThreads;
        public final boolean asyncPlayerdataSave;
        public final boolean asyncChunkSend;

        Async(YamlConfiguration cfg) {
            asyncPathfindingEnabled = cfg.getBoolean("async.pathfinding.enabled", true);
            asyncPathfindingMaxThreads = cfg.getInt("async.pathfinding.max-threads", 0);
            asyncPlayerdataSave = cfg.getBoolean("async.playerdata-save.enabled", true);
            asyncChunkSend = cfg.getBoolean("async.chunk-send.enabled", true);
            set(cfg);
        }

        private void set(YamlConfiguration cfg) {
            cfg.set("async.pathfinding.enabled", asyncPathfindingEnabled);
            cfg.set("async.pathfinding.max-threads", asyncPathfindingMaxThreads);
            cfg.set("async.playerdata-save.enabled", asyncPlayerdataSave);
            cfg.set("async.chunk-send.enabled", asyncChunkSend);
        }
    }

    public static final class Network {
        public final boolean viewDistanceDampeningEnabled;
        public final int vddMinViewDistance;
        public final int vddQueueHighThreshold;
        public final int vddQueueLowThreshold;

        Network(YamlConfiguration cfg) {
            viewDistanceDampeningEnabled = cfg.getBoolean("network.view-distance-dampening.enabled", true);
            vddMinViewDistance = cfg.getInt("network.view-distance-dampening.min-view-distance", 4);
            vddQueueHighThreshold = cfg.getInt("network.view-distance-dampening.queue-high-threshold", 24);
            vddQueueLowThreshold = cfg.getInt("network.view-distance-dampening.queue-low-threshold", 8);
            set(cfg);
        }

        private void set(YamlConfiguration cfg) {
            cfg.set("network.view-distance-dampening.enabled", viewDistanceDampeningEnabled);
            cfg.set("network.view-distance-dampening.min-view-distance", vddMinViewDistance);
            cfg.set("network.view-distance-dampening.queue-high-threshold", vddQueueHighThreshold);
            cfg.set("network.view-distance-dampening.queue-low-threshold", vddQueueLowThreshold);
        }
    }

    public static final class Misc {
        public final boolean suppressNonSecureMarkerLog;
        public final boolean suppressInvalidStatsLog;
        public final boolean suppressEmptyMessageLog;
        public final boolean suppressIgnoredAdvancementsLog;
        public final boolean suppressExpiredMessageLog;
        public final boolean suppressUnrecognizedRecipeLog;

        Misc(YamlConfiguration cfg) {
            suppressNonSecureMarkerLog = cfg.getBoolean("misc.suppress-logs.non-secure-marker", true);
            suppressInvalidStatsLog = cfg.getBoolean("misc.suppress-logs.invalid-statistics", true);
            suppressEmptyMessageLog = cfg.getBoolean("misc.suppress-logs.empty-message", true);
            suppressIgnoredAdvancementsLog = cfg.getBoolean("misc.suppress-logs.ignored-advancements", true);
            suppressExpiredMessageLog = cfg.getBoolean("misc.suppress-logs.expired-message", true);
            suppressUnrecognizedRecipeLog = cfg.getBoolean("misc.suppress-logs.unrecognized-recipe", true);
            set(cfg);
        }

        private void set(YamlConfiguration cfg) {
            cfg.set("misc.suppress-logs.non-secure-marker", suppressNonSecureMarkerLog);
            cfg.set("misc.suppress-logs.invalid-statistics", suppressInvalidStatsLog);
            cfg.set("misc.suppress-logs.empty-message", suppressEmptyMessageLog);
            cfg.set("misc.suppress-logs.ignored-advancements", suppressIgnoredAdvancementsLog);
            cfg.set("misc.suppress-logs.expired-message", suppressExpiredMessageLog);
            cfg.set("misc.suppress-logs.unrecognized-recipe", suppressUnrecognizedRecipeLog);
        }
    }
}

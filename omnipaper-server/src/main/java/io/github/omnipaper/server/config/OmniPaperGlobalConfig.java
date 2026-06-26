package io.github.omnipaper.server.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.jspecify.annotations.NullMarked;

import java.io.File;

@NullMarked
public final class OmniPaperGlobalConfig {

    public final Performance performance;
    public final Network network;
    public final Misc misc;

    private OmniPaperGlobalConfig(YamlConfiguration cfg) {
        this.performance = new Performance(cfg);
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
        public final boolean entityCullEnabled;
        public final int entityCullIntervalTicks;
        public final double particleCullDistance;
        public final double soundCullDistance;

        Performance(YamlConfiguration cfg) {
            enableAdaptiveTick = cfg.getBoolean("performance.adaptive-tick.enabled", true);
            adaptiveTickThresholdMs = cfg.getDouble("performance.adaptive-tick.threshold-ms", 55.0);
            adaptiveTickRecoveryMs = cfg.getDouble("performance.adaptive-tick.recovery-ms", 45.0);
            entityCullEnabled = cfg.getBoolean("performance.entity-cull.enabled", true);
            entityCullIntervalTicks = cfg.getInt("performance.entity-cull.interval-ticks", 200);
            particleCullDistance = cfg.getDouble("performance.particle-cull-distance", 48.0);
            soundCullDistance = cfg.getDouble("performance.sound-cull-distance", 48.0);
            set(cfg);
        }

        private void set(YamlConfiguration cfg) {
            cfg.set("performance.adaptive-tick.enabled", enableAdaptiveTick);
            cfg.set("performance.adaptive-tick.threshold-ms", adaptiveTickThresholdMs);
            cfg.set("performance.adaptive-tick.recovery-ms", adaptiveTickRecoveryMs);
            cfg.set("performance.entity-cull.enabled", entityCullEnabled);
            cfg.set("performance.entity-cull.interval-ticks", entityCullIntervalTicks);
            cfg.set("performance.particle-cull-distance", particleCullDistance);
            cfg.set("performance.sound-cull-distance", soundCullDistance);
        }
    }

    public static final class Network {
        public final int minViewDistance;
        public final boolean viewDistanceDampeningEnabled;

        Network(YamlConfiguration cfg) {
            viewDistanceDampeningEnabled = cfg.getBoolean("network.view-distance-dampening.enabled", true);
            minViewDistance = cfg.getInt("network.view-distance-dampening.min-view-distance", 4);
            set(cfg);
        }

        private void set(YamlConfiguration cfg) {
            cfg.set("network.view-distance-dampening.enabled", viewDistanceDampeningEnabled);
            cfg.set("network.view-distance-dampening.min-view-distance", minViewDistance);
        }
    }

    public static final class Misc {
        public final boolean suppressInvalidStatsLog;
        public final boolean suppressEmptyMessageLog;
        public final boolean suppressIgnoredAdvancementsLog;
        public final boolean suppressExpiredMessageLog;
        public final boolean suppressUnrecognizedRecipeLog;

        Misc(YamlConfiguration cfg) {
            suppressInvalidStatsLog = cfg.getBoolean("misc.suppress-logs.invalid-statistics", true);
            suppressEmptyMessageLog = cfg.getBoolean("misc.suppress-logs.empty-message", true);
            suppressIgnoredAdvancementsLog = cfg.getBoolean("misc.suppress-logs.ignored-advancements", true);
            suppressExpiredMessageLog = cfg.getBoolean("misc.suppress-logs.expired-message", true);
            suppressUnrecognizedRecipeLog = cfg.getBoolean("misc.suppress-logs.unrecognized-recipe", true);
            set(cfg);
        }

        private void set(YamlConfiguration cfg) {
            cfg.set("misc.suppress-logs.invalid-statistics", suppressInvalidStatsLog);
            cfg.set("misc.suppress-logs.empty-message", suppressEmptyMessageLog);
            cfg.set("misc.suppress-logs.ignored-advancements", suppressIgnoredAdvancementsLog);
            cfg.set("misc.suppress-logs.expired-message", suppressExpiredMessageLog);
            cfg.set("misc.suppress-logs.unrecognized-recipe", suppressUnrecognizedRecipeLog);
        }
    }
}
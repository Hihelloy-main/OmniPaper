package io.github.omnipaper.server.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NullMarked;

import java.io.File;
import java.io.IOException;

@NullMarked
public final class OmniPaperConfigurations {

    private static final Logger LOGGER = LogManager.getLogger("OmniPaper Config");
    private static final File CONFIG_DIR = new File("config");
    private static final String GLOBAL_FILE = "omnipaper.yml";

    private static volatile OmniPaperConfigurations INSTANCE;

    private final OmniPaperGlobalConfig globalConfig;

    private OmniPaperConfigurations(OmniPaperGlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    @NullMarked
    public static OmniPaperConfigurations setup() throws Exception {
        if (!CONFIG_DIR.exists() && !CONFIG_DIR.mkdirs()) {
            LOGGER.warn("Failed to create config directory");
        }
        File globalFile = new File(CONFIG_DIR, GLOBAL_FILE);
        OmniPaperGlobalConfig global = OmniPaperGlobalConfig.load(globalFile);
        INSTANCE = new OmniPaperConfigurations(global);
        LOGGER.info("OmniPaper config loaded from {}", globalFile.getPath());
        return INSTANCE;
    }

    @org.jspecify.annotations.Nullable
    public static OmniPaperConfigurations get() {
        return INSTANCE;
    }

    public OmniPaperGlobalConfig global() {
        return globalConfig;
    }

    public void reload() throws Exception {
        File globalFile = new File(CONFIG_DIR, GLOBAL_FILE);
        OmniPaperGlobalConfig newGlobal = OmniPaperGlobalConfig.load(globalFile);
        INSTANCE = new OmniPaperConfigurations(newGlobal);
        LOGGER.info("OmniPaper config reloaded");
    }
}

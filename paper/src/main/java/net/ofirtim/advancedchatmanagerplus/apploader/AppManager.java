package net.ofirtim.advancedchatmanagerplus.apploader;

import net.ofirtim.advancedchatmanagerplus.apploader.models.MinecraftAdapter;
import net.ofirtim.advancedchatmanagerplus.apploader.models.managers.PlayerManager;

public class AppManager {

    private final AppManager appManagerInstance;
    private final PaperAppLoader appLoader;

    public AppManager(PaperAppLoader ignoredPaperAppLoader) {
        this.appLoader = ignoredPaperAppLoader;
        this.appManagerInstance = this;
    }

    public MinecraftAdapter getCraftAdapter() {
        return appLoader.minecraftAdapterInstance;
    }

    public PlayerManager getPlayerManager() {
        return appLoader.playerManagerInstance;
    }
}

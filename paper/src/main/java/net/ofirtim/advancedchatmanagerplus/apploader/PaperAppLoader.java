package net.ofirtim.advancedchatmanagerplus.apploader;

import org.bukkit.plugin.java.JavaPlugin;

public final class PaperAppLoader extends JavaPlugin {

    private static PaperAppLoader instance;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("AdvancedChatManagerPlus v" + getPluginMeta().getVersion() + "is now enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().warning("AdvancedChatManagerPlus has been disabled, goodbye!");
    }

    public static PaperAppLoader getInstance() {
        return instance;
    }
}

package org.example.advancedChatManagerPlus;

import org.bukkit.plugin.java.JavaPlugin;

public final class PaperAppLoader extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("AdvancedChatManagerPlus v" + getPluginMeta().getVersion() + "is now enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().warning("AdvancedChatManagerPlus has been disabled, goodbye!");
    }
}

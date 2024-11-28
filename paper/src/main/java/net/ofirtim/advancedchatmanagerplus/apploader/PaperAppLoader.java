/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus.apploader;

import net.ofirtim.advancedchatmanagerplus.apploader.models.MinecraftAdapter;
import net.ofirtim.advancedchatmanagerplus.apploader.models.managers.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PaperAppLoader extends JavaPlugin {

    private static PaperAppLoader instance;
    private static AppManager appManager;
    MinecraftAdapter minecraftAdapterInstance;
    PlayerManager playerManagerInstance;

    @Override
    public void onEnable() {
        instance = this;
        appManager = new AppManager(this);
        this.minecraftAdapterInstance = new MinecraftAdapter(this);
        this.playerManagerInstance = new PlayerManager();
        new AppProcedureStartup(this);
        getLogger().info("AdvancedChatManagerPlus v" + getDescription().getVersion() + "is now enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().warning("AdvancedChatManagerPlus has been disabled, goodbye!");
    }

    public static PaperAppLoader getInstance() {
        return instance;
    }
}

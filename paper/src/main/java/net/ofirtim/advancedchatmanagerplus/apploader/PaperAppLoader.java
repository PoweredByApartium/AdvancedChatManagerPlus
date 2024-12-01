/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus.apploader;

import net.ofirtim.advancedchatmanagerplus.ACMPimpl;
import net.ofirtim.advancedchatmanagerplus.apploader.models.MinecraftAdapter;
import net.ofirtim.advancedchatmanagerplus.flatfile.FlatFileDataConnector;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class PaperAppLoader extends JavaPlugin {

    private static PaperAppLoader instance;
    MinecraftAdapter minecraftAdapterInstance;
    ACMPimpl acmp;

    @Override
    public void onEnable() {
        instance = this;
        acmp = new ACMPimpl();
        SpigotPlatformConnector platformConnector = new SpigotPlatformConnector();
        FlatFileDataConnector file = new FlatFileDataConnector(getDataFolder(), platformConnector);
        try {
            file.connect(getDataFolder(), Map.of());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        acmp.setDataConnector(file);
        this.minecraftAdapterInstance = new MinecraftAdapter(this);
        new AppProcedureStartup(this);
        getLogger().info("AdvancedChatManagerPlus v" + getDescription().getVersion() + " is now enabled!");
    }

    public ACMPimpl getAcmp() {
        return acmp;
    }

    @Override
    public void onDisable() {
        getLogger().warning("AdvancedChatManagerPlus has been disabled, goodbye!");
    }

    public static PaperAppLoader getInstance() {
        return instance;
    }
}

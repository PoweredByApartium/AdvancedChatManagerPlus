/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus.apploader.models;

import net.ofirtim.advancedchatmanagerplus.ChatPlayer;
import net.ofirtim.advancedchatmanagerplus.apploader.AppManager;
import net.ofirtim.advancedchatmanagerplus.apploader.PaperAppLoader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MinecraftAdapter {

    public MinecraftAdapter(PaperAppLoader paperAppLoader) {}

    public ChatPlayer from(@NotNull Player player) {
        return AppManager.getPlayer(player.getUniqueId());
    }

    public Optional<Player> to(@NotNull ChatPlayer chatPlayer) {
        Player player = Bukkit.getPlayer(chatPlayer.getUniqueId());
        return Optional.ofNullable(player);
    }
}

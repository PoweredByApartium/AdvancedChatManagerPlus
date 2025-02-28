/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus.apploader.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.ofirtim.advancedchatmanagerplus.ChatChannel;
import net.ofirtim.advancedchatmanagerplus.ChatPlayer;
import net.ofirtim.advancedchatmanagerplus.apploader.PaperAppLoader;
import net.ofirtim.advancedchatmanagerplus.state.ChatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChatListener implements Listener {

    private final PaperAppLoader paperAppLoader;
    private ChatManager chatManager;

    public PlayerChatListener(PaperAppLoader paperAppLoader) {
        this.paperAppLoader = paperAppLoader;
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        ChatPlayer chatPlayer = PaperAppLoader.getMinecraftAdapter().from(player);

    }
}

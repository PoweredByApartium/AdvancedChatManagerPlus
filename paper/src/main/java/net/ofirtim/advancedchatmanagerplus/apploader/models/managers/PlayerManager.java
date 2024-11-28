/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus.apploader.models.managers;

import net.ofirtim.advancedchatmanagerplus.ChatPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private static final PlayerManager instance = new PlayerManager();

    private static final Map<UUID, ChatPlayer> playerCache = new HashMap<>();

    public ChatPlayer getPlayer(UUID uuid) {
        return playerCache.computeIfAbsent(uuid,
                key -> new ChatPlayer(uuid));
    }

    //------------------------------------- BOILERPLATE ----------------------------------------///
    public static Map<UUID, ChatPlayer> getPlayerCache() {
        return Map.copyOf(playerCache);
    }

    public static PlayerManager getInstance() {
        return instance;
    }
}

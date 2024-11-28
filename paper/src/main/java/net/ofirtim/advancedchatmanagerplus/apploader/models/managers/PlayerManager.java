package net.ofirtim.advancedchatmanagerplus.apploader.models.managers;

import net.ofirtim.advancedchatmanagerplus.api.structs.ChatPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private static final PlayerManager instance = new PlayerManager();

    private static final Map<UUID, ChatPlayer> playerCache = new HashMap<>();

    public ChatPlayer getPlayer(UUID uuid) {
        return playerCache.computeIfAbsent(uuid,
                (key) -> new ChatPlayer(uuid));
    }

    //------------------------------------- BOILERPLATE ----------------------------------------///
    public static Map<UUID, ChatPlayer> getPlayerCache() {
        return Map.copyOf(playerCache);
    }

    public static PlayerManager getInstance() {
        return instance;
    }
}

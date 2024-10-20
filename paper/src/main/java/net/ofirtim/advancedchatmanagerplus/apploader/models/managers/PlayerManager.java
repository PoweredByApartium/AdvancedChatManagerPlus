package net.ofirtim.advancedchatmanagerplus.apploader.models.managers;

import net.ofirtim.advancedchatmanagerplus.api.PluginPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private static final PlayerManager instance = new PlayerManager();

    private static final Map<UUID, PluginPlayer> playerCache = new HashMap<>();

    public PluginPlayer getPlayer(UUID uuid) {
        return playerCache.computeIfAbsent(uuid,
                (key) -> new PluginPlayer(uuid));
    }

    public static Map<UUID, PluginPlayer> getPlayerCache() {
        return Map.copyOf(playerCache);
    }

    public static PlayerManager getInstance() {
        return instance;
    }
}

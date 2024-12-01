package net.ofirtim.advancedchatmanagerplus.state;

import net.ofirtim.advancedchatmanagerplus.ChatPlayer;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {

    private final Map<UUID, ChatPlayer> playerCache = new ConcurrentHashMap<>();

    public ChatPlayer getPlayer(UUID uuid) {
        return playerCache.get(uuid);
    }

    public void insertPlayer(ChatPlayer chatPlayer) {
        playerCache.put(chatPlayer.getUniqueId(), chatPlayer);
    }

}

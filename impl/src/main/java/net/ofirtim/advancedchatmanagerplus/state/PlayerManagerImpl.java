package net.ofirtim.advancedchatmanagerplus.state;

import net.ofirtim.advancedchatmanagerplus.ChatChannel;
import net.ofirtim.advancedchatmanagerplus.ChatPlayer;
import net.ofirtim.advancedchatmanagerplus.Tag;
import net.ofirtim.advancedchatmanagerplus.models.PlayerManager;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManagerImpl implements PlayerManager {

    private final Map<UUID, ChatPlayer> playerCache = new ConcurrentHashMap<>();

    public Optional<ChatPlayer> getPlayer(UUID uuid) {
        return Optional.ofNullable(playerCache.get(uuid));
    }

    public void insertPlayer(ChatPlayer chatPlayer) {
        playerCache.put(chatPlayer.getUniqueId(), chatPlayer);
    }


    @Override
    public Optional<ChatPlayer> getPlayerFromName(String playerName) {
        return Optional.empty();
    }

    @Override
    public Set<ChatPlayer> getOnlinePlayers() {
        return Set.of();
    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public void setChatChannel(ChatPlayer player, ChatChannel chatChannel) {

    }

    @Override
    public void addTag(ChatPlayer player, Tag tag) {

    }

    @Override
    public CompletableFuture<Boolean> removeTag(ChatPlayer player, Tag tag) {
        return null;
    }
}

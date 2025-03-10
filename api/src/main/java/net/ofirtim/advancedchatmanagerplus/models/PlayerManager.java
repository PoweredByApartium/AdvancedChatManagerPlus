/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus.models;

import net.ofirtim.advancedchatmanagerplus.ChatChannel;
import net.ofirtim.advancedchatmanagerplus.ChatPlayer;
import net.ofirtim.advancedchatmanagerplus.Tag;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Class used to control and update player's information related to ACMP.
 * This class is the equivalent to Bukkit's main class caller.
 */
public interface PlayerManager {

    /**
     * Gets the {@link ChatPlayer} object with the information filled in as an optional.
     * This is the equivalent to Bukkit's player object to this plugin's information.
     * @param playerUniqueId the player's UniqueId, can be found on namemc.com or any other site that grabs skins & user info.
     * @return an {@link Optional} of {@link ChatPlayer}, if the ChatPlayer was found in database, the optional will not be empty (null).
     */
    Optional<ChatPlayer> getPlayer(UUID playerUniqueId);

    /**
     * Gets the {@link ChatPlayer}, object with the information filled as an optional.
     * This is the equivalent to Bukkit's player object to this plugin's information.
     * this command is identical to {@link PlayerManager#getPlayer(UUID)}, but allows getting a ChatPlayer object from its username and not unique id.
     * @param playerName the player's id.
     * @return an {@link Optional} of {@link ChatPlayer}, if the ChatPlayer was found in database, the optional will not be empty (null).
     */
    Optional<ChatPlayer> getPlayerFromName(String playerName);

    /**
     * This method does not return a direct Set to memory's online ChatPlayer's and is unmodifiable.
     * @return a set of online players, equivalent to bukkit's getOnlinePlayers only with ACMP's data.
     */
    Set<ChatPlayer> getOnlinePlayers();

    /**
     * Players can choose their own game language as long as there server support translation to that language.
     * @return The Locale object of the player's language.
     */
    Locale getLocale();

    /**
     * Sets the player's chat channel in permanent context, until changed otherwise.
     * @param player The ChatPlayer reference object.
     * @param chatChannel The chat channel to assign to the player.
     */
    void setChatChannel(ChatPlayer player, ChatChannel chatChannel);

    /**
     * Adds a "tag" to the player's chained tags.
     * @param player The ChatPlayer reference object.
     * @param tag The tag you are looking to add.
     */
    void addTag(ChatPlayer player, Tag tag);

    /**
     * Removes a "tag" from the player's chained tags.
     * This method avoids thread blocking by using completable futures.
     * This method will return a boolean if the tag was removed or not.
     * Usually if the boolean returns false it means it couldn't remove the tag, because it had something
     * to do with a permission that is related to the tag, or this tag is a default tag for the player.
     * if the boolean returns null, it means the player never had this tag.
     * @param player The ChatPlayer reference object.
     * @param tag The tag you are looking to remove.
     * @return a {@link CompletableFuture} of a Boolean for asynchronous means.
     */
    CompletableFuture<Boolean> removeTag(ChatPlayer player, Tag tag);
}

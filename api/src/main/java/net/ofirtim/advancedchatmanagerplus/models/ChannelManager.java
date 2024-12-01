/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus.models;

import net.ofirtim.advancedchatmanagerplus.ChatChannel;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ChannelManager {

    /**
     * Tries to get the chat channel targeted.
     * @param channelId the channel id (is a string).
     * @return an {@link Optional} that is either empty or with the chat channel object.
     */
    Optional<ChatChannel> getChannel(String channelId);

    /**
     * obtains an immutable copy of all the chat channels the player is in.
     * This method does not co-respond with critical changes like channel name, channel type or its permission.
     * @param playerUniqueId the player's uniqueId.
     * @return an immutable {@link Set} containing all the channels this player participates in (all chat channel objects are immutable and not runtime relevant).
     */
    Set<ChatChannel> getChannelsFromPlayer(UUID playerUniqueId);

    /**
     * This method returns a Kyori MiniMessage for chat control purposes.
     * All info about Kyori's MiniMessage can be found in the  <a href="https://docs.advntr.dev/minimessage/index.html">following link</a>.
     * @param channel The ChatChannel object.
     * @return A {@link String} containing a Kyori's MiniMessage.
     */
    String getChannelFormat(ChatChannel channel);

    /**
     * Gets all existing chat channels that are not in-configuration.
     * Will not get GLOBAL or any channel that is in the configuration files.
     * @return A {@link Set} of {@link ChatChannel}'s that are ephemeral.
     */
    Set<ChatChannel> getEphemeralChannels();

    /**
     * Creates an ephemeral chat channel (temporary for short).
     * ChatChannels like this can be created for parties or factions.
     * These channels are not in-configuration, they simply stay in memory.
     * @param members Set of members of the channel.
     * @return The UniqueId of the channel.
     */
    UUID createChannel(Set<UUID> members);
}

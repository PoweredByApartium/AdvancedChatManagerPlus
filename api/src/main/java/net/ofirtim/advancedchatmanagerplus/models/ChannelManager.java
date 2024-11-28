/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus.models;

import net.ofirtim.advancedchatmanagerplus.ChatChannel;
import net.ofirtim.advancedchatmanagerplus.Issuer;

import java.util.Set;
import java.util.UUID;

public interface ChannelManager {

    public ChatChannel getChannel(String channelId);

    public Set<ChatChannel> getChannelsFromOwner(Issuer issuer);

    public Set<ChatChannel> getChannelsFromPlayer(UUID playerUniqueId);

    public String getChannelFormat(ChatChannel channel);

}

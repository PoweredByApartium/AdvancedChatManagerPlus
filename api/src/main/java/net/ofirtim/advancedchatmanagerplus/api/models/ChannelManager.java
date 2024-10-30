package net.ofirtim.advancedchatmanagerplus.api.models;

import net.ofirtim.advancedchatmanagerplus.api.structs.ChatChannel;
import net.ofirtim.advancedchatmanagerplus.api.structs.Issuer;

import java.util.Set;
import java.util.UUID;

public interface ChannelManager {

    public ChatChannel getChannel(String channelId);

    public Set<ChatChannel> getChannelsFromOwner(Issuer issuer);

    public Set<ChatChannel> getChannelsFromPlayer(UUID playerUniqueId);

    public String getChannelFormat(ChatChannel channel);

}

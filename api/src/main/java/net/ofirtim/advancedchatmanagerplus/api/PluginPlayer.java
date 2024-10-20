package net.ofirtim.advancedchatmanagerplus.api;

import net.ofirtim.advancedchatmanagerplus.api.structs.Channel;

import java.util.UUID;

public class PluginPlayer {

    private final UUID UniqueId;
    private Channel channel;

    //Constructor
    public PluginPlayer(UUID uuid) {
        this.UniqueId = uuid;
    }



    public void setChannel(Channel channel) {
        //TODO !
    }

    public Channel getChannel() {
        return channel;
    }

    public UUID getUniqueId() {
        return UniqueId;
    }
}

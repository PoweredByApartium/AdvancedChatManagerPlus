package net.ofirtim.advancedchatmanagerplus.api.structs;

import java.util.List;
import java.util.UUID;

public class ChatPlayer {

    private final UUID UniqueId;
    private ChatChannel chatChannel;
    private List<Tag> prefixes;

    //Constructor
    public ChatPlayer(UUID uuid) {
        this.UniqueId = uuid;
        //Fetch player data from db or else.
    }

    public ChatChannel getChatChannel() {
        return chatChannel;
    }

    public UUID getUniqueId() {
        return UniqueId;
    }
}

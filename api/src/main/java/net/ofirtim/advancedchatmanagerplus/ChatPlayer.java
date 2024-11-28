package net.ofirtim.advancedchatmanagerplus.api.structs;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class ChatPlayer {

    private final UUID UniqueId;
    private ChatChannel chatChannel;
    private List<Tag> prefixes;
    private Locale locale;

    //Constructor
    public ChatPlayer(UUID uuid) {
        this.UniqueId = uuid;
        //Fetch player data from db or else.
    }

    public Locale getLocale() {
        return locale;
    }

    /* package-private */ void setLocale(Locale locale) {
        this.locale = locale;
    }

    /* package-private */ void setPrefixes(List<Tag> prefixes) {
        this.prefixes = prefixes;
    }

    /* package-private */ void setChatChannel(ChatChannel channel) {
        this.chatChannel = channel;
    }

    /* package-private */ void markReady() {

    }

    public ChatChannel getChatChannel() {
        return chatChannel;
    }

    public UUID getUniqueId() {
        return UniqueId;
    }
}

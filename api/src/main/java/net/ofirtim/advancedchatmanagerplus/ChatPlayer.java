/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class ChatPlayer {

    private final UUID uniqueId;
    private ChatChannel chatChannel;
    private List<Tag> prefixes;
    private Locale locale;

    //Constructor
    public ChatPlayer(UUID uuid) {
        this.uniqueId = uuid;
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
        return uniqueId;
    }
}

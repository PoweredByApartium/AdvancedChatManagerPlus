package net.ofirtim.advancedchatmanagerplus.api.structs;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatChannel {

    private Issuer owner;
    private String name;

    @Nullable
    private String requiredPermission;

    private List<String> allowedRanks = new ArrayList<>();

    public static final ChatChannel
            GLOBAL = new ChatChannel("chat", Issuer.CONSOLE, "acmp.channel.global.allow"),
            STAFF_CHAT = new ChatChannel("staff", Issuer.DEFAULT_ISSUER, "acmp.channel.staff.allow");

    public ChatChannel(String name, Issuer owner, @Nullable String requiredPermission, List<String> allowedRanks) {
        this.owner = owner;
        this.name = name;
        this.requiredPermission = requiredPermission;
        this.allowedRanks = allowedRanks;
    }

    public ChatChannel(String name, Issuer owner, String requiredPermission) {
        new ChatChannel(name, owner, requiredPermission, Collections.emptyList());
    }

    public String getName() {
        return name;
    }

    public Issuer getOwner() {
        return owner;
    }

    @Nullable
    public String getRequiredPermission() {
        return requiredPermission;
    }
}

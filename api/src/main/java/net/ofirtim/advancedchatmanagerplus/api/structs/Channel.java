package net.ofirtim.advancedchatmanagerplus.api.structs;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Channel {

    private Issuer owner;
    private String name;

    @Nullable
    private String requiredPermission;

    private List<String> allowedRanks = new ArrayList<>();

    public static final Channel
            GLOBAL = new Channel("chat", Issuer.CONSOLE, "acmp.channel.global.allow"),
            STAFF_CHAT = new Channel("staff", Issuer.DEFAULT_ISSUER, "acmp.channel.staff.allow");

    public Channel(String name, Issuer owner, @Nullable String requiredPermission, List<String> allowedRanks) {
        this.owner = owner;
        this.name = name;
        this.requiredPermission = requiredPermission;
        this.allowedRanks = allowedRanks;
    }

    public Channel(String name, Issuer owner, String requiredPermission) {
        new Channel(name, owner, requiredPermission, Collections.emptyList());
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

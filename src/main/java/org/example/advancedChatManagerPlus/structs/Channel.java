package org.example.advancedChatManagerPlus.structs;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    private String name, owner, requiredPermission;

    private List<String> allowedRanks = new ArrayList<>();

    public Channel(String name, String owner, String requiredPermission) {
        this.name = name;
        this.owner = owner;
        this.requiredPermission = requiredPermission;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getRequiredPermission() {
        return requiredPermission;
    }
}

/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Issuer {

    @Nullable
    private final String name;

    @Nullable
    private final UUID uuid;

    private final IssuerType type;

    public static final Issuer
            CONSOLE = new Issuer("console", IssuerType.CONSOLE,null),
            /**The plugin creator is considered a filler port for most fields that require a default issuer.*/
            DEFAULT_ISSUER = new Issuer("OfirTIM", IssuerType.PLAYER, UUID.fromString("b410f5ec-3fe9-4567-a544-1907e93a6f6d"));


    //------------------------------------- BOILERPLATE ----------------------------------------///
    public Issuer(@Nullable String name, IssuerType issuerType, @Nullable UUID uuid) {
        this.uuid = uuid;
        this.name = name;
        this.type = issuerType;
    }

    public boolean isConsole() {
        return this == CONSOLE;
    }

    public boolean isPlugin() {
        return this.type == IssuerType.PLUGIN;
    }

    public boolean isPlayer() {
        return this.type == IssuerType.PLAYER;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public UUID getUniqueId() {
        return uuid;
    }

    public enum IssuerType {
        PLAYER,
        CONSOLE,
        PLUGIN,
        CUSTOM;
    }
}

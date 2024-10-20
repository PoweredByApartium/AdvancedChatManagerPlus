package net.ofirtim.advancedchatmanagerplus.api.structs;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Issuer {

    @Nullable
    private final String name;

    @Nullable
    private final UUID uuid;

    private final IssuerType type;

    public static final Issuer
            CONSOLE = new Issuer("console", IssuerType.CONSOLE,null);

    /**
     * The plugin creator is considered a filler port for most fields that require an issuer.
     */
    public static final Issuer DEFAULT_ISSUER = new Issuer("OfirTIM", IssuerType.PLAYER, UUID.fromString("b410f5ec-3fe9-4567-a544-1907e93a6f6d"));


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

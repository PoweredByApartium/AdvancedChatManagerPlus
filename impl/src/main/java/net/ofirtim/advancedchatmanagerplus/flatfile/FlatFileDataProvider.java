/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus.flatfile;

import net.ofirtim.advancedchatmanagerplus.ACMPConfiguration;
import net.ofirtim.advancedchatmanagerplus.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class FlatFileDataProvider implements DataProvider {

    private final FlatFileDataConnector parent;

    public FlatFileDataProvider(FlatFileDataConnector parent) {
        this.parent = parent;
    }

    @Override
    public ACMPConfiguration loadConfiguration() {
        File file = new File(parent.dataFolder, "config.json");
        try {
            return parent.objectMapper.readValue(file, ACMPConfiguration.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompletableFuture<PlayerJoinDataResponse> getDataOnJoin(UUID player) {
        return parent.platformConnector.supplyAsync(() -> {
            File file = new File(parent.dataFolder, String.format("players/%s.json", player));
            return parent.objectMapper.readValue(file, PlayerJoinDataResponse.class);
        });
    }
}

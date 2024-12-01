/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus.flatfile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import net.ofirtim.advancedchatmanagerplus.DataConnector;
import net.ofirtim.advancedchatmanagerplus.DataProvider;
import net.ofirtim.advancedchatmanagerplus.PlatformConnector;
import net.ofirtim.advancedchatmanagerplus.TranslationProvider;

import java.io.File;
import java.util.Map;

public class FlatFileDataConnector implements DataConnector {

    /* package-private */ final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /* package-private */ final File dataFolder;

    /* package-private */ final PlatformConnector platformConnector;

    private FlatFileDataProvider flatFileDataProvider;

    private FlatFileTranslationProvider flatFileTranslationProvider;

    private boolean connected;

    public FlatFileDataConnector(File dataFolder, PlatformConnector platformConnector) {
        this.platformConnector = platformConnector;
        this.dataFolder = dataFolder;
    }

    @Override
    public TranslationProvider getTranslationProvider() {
        ensureLoaded();
        return flatFileTranslationProvider;
    }

    @Override
    public DataProvider getDataProvider() {
        ensureLoaded();
        return flatFileDataProvider;
    }

    @Override
    public void connect(File dataFolder, Map<String, Object> params) throws Exception {
        this.flatFileDataProvider = new FlatFileDataProvider(this);
        this.flatFileTranslationProvider = new FlatFileTranslationProvider(this);
    }

    private void ensureLoaded() {
        if (!connected)
            throw new RuntimeException("Not connected");
    }

}

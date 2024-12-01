/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus.flatfile;

import com.fasterxml.jackson.annotation.JsonCreator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.ofirtim.advancedchatmanagerplus.ChatPlayer;
import net.ofirtim.advancedchatmanagerplus.Message;
import net.ofirtim.advancedchatmanagerplus.TranslationProvider;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class FlatFileTranslationProvider implements TranslationProvider {

    private final FlatFileDataConnector parent;

    Map<String, Map<String, Component>> translations;

    MiniMessage miniMessage = MiniMessage.miniMessage();

    public FlatFileTranslationProvider(FlatFileDataConnector parent) throws Exception {
        this.parent = parent;
        load();
    }

    private void load() throws Exception {
        File file = new File(parent.dataFolder, "translations.json");
        TranslationFile translationFile = parent.objectMapper.readValue(file, TranslationFile.class);
        Map<String, Map<String, Component>> translationsTemp = new HashMap<>();
        translationFile.content().forEach((key, content) -> {
            Map<String, Component> temp = new HashMap<>();
            content.forEach((lang, value) -> {
                Component translated = miniMessage.deserialize(value);
                temp.put(key, translated);
            });
            translationsTemp.put(key, Map.copyOf(temp));
        });

        this.translations = Map.copyOf(translationsTemp);

    }

    @Override
    public Optional<Component> translate(Message message, Locale locale) {
        return Optional.ofNullable(this.translations.get(message.getMessageKey()))
                .map(map -> map.get(locale.getLanguage()));
    }

    @Override
    public Optional<Component> translate(Message message, ChatPlayer player) {
        return translate(message, player.getLocale());
    }

    @Override
    public Message getDynamic(String key) {
        return new Message(key, key, Message.MessageSeverity.GENERAL);
    }

    record TranslationFile(Map<String, Map<String, String>> content) {
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        public TranslationFile {

        }
    }

}

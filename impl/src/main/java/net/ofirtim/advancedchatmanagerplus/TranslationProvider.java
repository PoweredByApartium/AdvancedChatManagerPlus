/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus;

import net.kyori.adventure.text.Component;

import java.util.Locale;
import java.util.Optional;

public interface TranslationProvider {

    Optional<Component> translate(Message message, Locale locale);

    Optional<Component> translate(Message message, ChatPlayer player);

    Message getDynamic(String key);

}

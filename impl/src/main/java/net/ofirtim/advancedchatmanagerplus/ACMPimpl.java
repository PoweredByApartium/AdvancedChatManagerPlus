/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus;

public class ACMPimpl implements ACMP {

    DataProvider dataProvider;

    TranslationProvider translationProvider;

    @Override
    public void markForInit(ChatPlayer player) {
         dataProvider.getDataOnJoin(player.getUniqueId())
                 .thenAccept(data -> {
                     player.setChatChannel(data.channel());
                     player.setPrefixes(data.prefixes());
                     player.setLocale(data.locale());
                     player.markReady();
                 });
    }

    public TranslationProvider getTranslationProvider() {
        return translationProvider;
    }

}

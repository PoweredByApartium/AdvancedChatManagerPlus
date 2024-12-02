/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus;

import net.ofirtim.advancedchatmanagerplus.state.PlayerManagerImpl;

public class ACMPimpl implements ACMP {

    private final PlayerManagerImpl playerManager = new PlayerManagerImpl();

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
                     playerManager.insertPlayer(player);
                 });
    }

    public void setDataConnector(DataConnector dataConnector) {
        this.dataProvider = dataConnector.getDataProvider();
        this.translationProvider = dataConnector.getTranslationProvider();
    }

    public PlayerManagerImpl getPlayerManager() {
        return playerManager;
    }

    public TranslationProvider getTranslationProvider() {
        return translationProvider;
    }

}

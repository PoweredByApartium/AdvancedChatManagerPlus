/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface DataProvider {

    CompletableFuture<PlayerJoinDataResponse> getDataOnJoin(UUID player);

    record PlayerJoinDataResponse(
            ChatChannel channel,
            List<Tag> prefixes,
            Locale locale
    ) {}
}

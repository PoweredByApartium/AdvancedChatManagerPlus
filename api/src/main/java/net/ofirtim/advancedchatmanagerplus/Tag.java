/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus;

public record Tag(
        String translationKey,
        int weight,
        String miniMessage) {

    public static final Tag
            GUEST_TAG = new Tag("guest", 1, "<gray>Guest</gray>"),
            MEMBER_TAG = new Tag("member", 2, "<gray>Member</gray>");
}

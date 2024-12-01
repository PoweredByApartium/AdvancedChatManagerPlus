/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus;

public class Permission {

    /**Declares Default loader option so we know the Permission class is getting loaded from this instance**/
    public static final String DEFAULT_PERMISSION = "acmp.default-loader";

    public static final String
            PLAYER_GLOBAL_CHAT = ChatChannel.GLOBAL.requiredPermission() + ".allow",
            PLAYER_GLOBAL_COLOR = ChatChannel.GLOBAL.requiredPermission() + ".color",
            PLAYER_GLOBAL_PLACEHOLDERS = ChatChannel.GLOBAL.requiredPermission() + ".placeholder.",

            COMMAND_RELOAD = "acmp.command.reload";
}

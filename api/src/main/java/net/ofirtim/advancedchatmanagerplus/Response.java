package net.ofirtim.advancedchatmanagerplus;

import java.util.EnumMap;

public record Response(
        ChatFilter.ActionResult actionResult,
        EnumMap<ChatFilter.ChatViolation, Integer> violations
) {
}

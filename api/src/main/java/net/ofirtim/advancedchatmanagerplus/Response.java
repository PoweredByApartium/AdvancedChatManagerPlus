package net.ofirtim.advancedchatmanagerplus;

import java.util.EnumMap;
import java.util.Map;

public record Response(
        ChatFilter.ActionResult actionResult,
        EnumMap<ChatFilter.ChatViolation, Integer> violations
) {
}

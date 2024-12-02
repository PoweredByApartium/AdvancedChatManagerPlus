package net.ofirtim.advancedchatmanagerplus;

import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ChatFilter is the main system to collect, verify and accept messages of players, generally allowing the filtration process to be super simplified
 */
public interface ChatFilter {

    /**
     * Regex patterns to seamlessly catch in-message obfuscations.
     * @return A regex pattern.
     */
    Pattern getFilterPattern();

    /**
     * Chat Violation that is related to this ChatFilter.
     * @return A ChatViolation.
     */
    ChatViolation getRelatedChatViolation();

    /**
     * Optionally server configurators can use the violation counter system,
     * allowing the server to deny player messages after certain violation count or even punish them as well.
     * @param message The message to check.
     * @return An Immutable {@link Map}, with {@link ChatViolation} as the key, and {@link Integer} as the amount of violations of the specific key ChatViolation.
     */
    default EnumMap<ChatViolation, Integer> getViolations(String message) {
        EnumMap<ChatViolation, Integer> violations = new EnumMap<>(Map.of(getRelatedChatViolation(), 0));

        Matcher matcher = getFilterPattern().matcher(message);
        while (matcher.find()) {
            violations.put(getRelatedChatViolation(), violations.get(getRelatedChatViolation()) + 1);
        }

        return violations;
    }


    /**
     * Method created to deobfuscate strings, the deobfuscation must be specified in continued implementations.
     * @param possiblyObfuscated A possibly obfuscated string/message.
     * @return A deobfuscated {@link String}, using the {@link ChatFilter#getFilterPattern()} as pattern matcher for obfuscation.
     */
    String deobfuscate(String possiblyObfuscated);

    /**
     * An enum dedicated to possible Chat Violations users might be able to pull through.
     */
    enum ChatViolation {
        ADDRESS,
        NUMBER_OBFUSCATION,
        SYMBOL_OBFUSCATION,
        SPACER_OBFUSCATION,
        REPEATED_CHARS,
        SWEARING,
        SPAMMING
    }

    enum ActionResult {
        IMMEDIATE_ACTION,
        DENY,
        ALLOW
    }
}

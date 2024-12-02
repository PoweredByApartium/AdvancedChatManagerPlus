package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SwearingChatFilter implements ChatFilter {

    List<String> words = new ArrayList<>();

    //TODO when config initiates make sure to pull all words from there.
    public final List<String> getBlockedWordsFromConfig() {
        return words;
    }

    @Override
    public ChatViolation getRelatedChatViolation() {
        return ChatViolation.SWEARING;
    }

    @Override
    public String deobfuscate(String possiblyObfuscated) {
        return possiblyObfuscated;
    }

    @Override
    public Pattern getFilterPattern() {
        // Combine words into a single regex for inner-word detection
        String regex = getBlockedWordsFromConfig().stream()
                .map(Pattern::quote) // Escapes special characters in the blocked words
                .reduce((a, b) -> a + "|" + b) // Combine into alternations (word1|word2|...)
                .orElse(""); // Empty string if no blocked words

        return Pattern.compile("(?i)(" + regex + ")"); // Case-insensitive pattern
    }
}

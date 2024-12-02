package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.List;
import java.util.regex.Pattern;

public class SwearingChatFilter implements ChatFilter {

    private final List<String> blockedWords = List.of("noob", "sex", "fuck");

    //TODO when config initiates make sure to pull all words from there.
    public List<String> getBlockedWords() {
        return List.copyOf(blockedWords);
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
        String regex = getBlockedWords().stream()
                .map(Pattern::quote)
                .reduce((a, b) -> a + "|" + b)
                .orElse("");

        return Pattern.compile("(?i)(" + regex + ")");
    }
}

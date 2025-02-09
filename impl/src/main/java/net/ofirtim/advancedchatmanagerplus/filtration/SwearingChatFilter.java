package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwearingChatFilter implements ChatFilter {

    private final List<String> blockedWords = List.of("noob", "sex", "fuck", "faggot", "ass", "asshole", "shit", "dick", "pussy", "cunt");

    //TODO when config initiates make sure to pull all words from there.
    public List<String> getBlockedWords() {
        return List.copyOf(blockedWords);
    }

    @Override
    public ChatViolation getRelatedChatViolation() {
        return ChatViolation.SWEARING;
    }

    @Override
    public EnumMap<ChatViolation, Integer> getViolations(String message) {
        EnumMap<ChatViolation, Integer> violations = new EnumMap<>(Map.of(getRelatedChatViolation(), 0));

        Matcher matcher = getFilterPattern().matcher(message);
        while(matcher.find()) violations.put(getRelatedChatViolation(), violations.get(getRelatedChatViolation()) + 1);
        return violations;
    }

    @Override
    public String deobfuscate(String possiblyObfuscated) {
        return possiblyObfuscated;
    }

    public Pattern getFilterPattern() {
        String regex = getBlockedWords().stream()
                .map(Pattern::quote)
                .reduce((a, b) -> a + "|" + b)
                .orElse("");

        return Pattern.compile("(?i)(" + regex + ")");
    }
}

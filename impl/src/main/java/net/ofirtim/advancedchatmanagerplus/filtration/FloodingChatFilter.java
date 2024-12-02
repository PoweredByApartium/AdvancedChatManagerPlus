package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FloodingChatFilter implements ChatFilter {


    public int getRepeatedCharThreshold() {
        return 5;
    }

    public int getRepeatedWordThreshold() {
        return 7;
    }

    @Override
    public Pattern getFilterPattern() {
        String charPattern = "(.)\\1{" + (getRepeatedCharThreshold() - 1) + ",}";
        String wordPattern = "\\b(\\w+)\\b(?:\\s*\\1\\b){" + (getRepeatedWordThreshold() - 1) + ",}";
        return Pattern.compile(charPattern + "|" + wordPattern);
    }

    @Override
    public EnumMap<ChatViolation, Integer> getViolations(String message) {
        EnumMap<ChatViolation, Integer> violations = new EnumMap<>(Map.of(getRelatedChatViolation(), 0));

        Matcher matcher = getFilterPattern().matcher(message);
        while(matcher.find()) violations.put(getRelatedChatViolation(), violations.get(getRelatedChatViolation()) + 1);
        return violations;
    }

    @Override
    public ChatViolation getRelatedChatViolation() {
        return ChatViolation.REPEATED_CHARS;
    }

    @Override
    public String deobfuscate(String possiblyObfuscated) {
        return possiblyObfuscated;
    }
}

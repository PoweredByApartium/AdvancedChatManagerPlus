package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.List;
import java.util.regex.Pattern;

public class SwearingChatFilter implements ChatFilter {

    public List<String> getBlockedWordsFromConfig() {
        return List.of("");
    }

    @Override
    public ChatViolation getRelatedChatViolation() {
        return ChatViolation.SWEARING;
    }

    @Override
    public String deobfuscate(String possiblyObfuscated) {
        return "";
    }

    @Override
    public Pattern getFilterPattern() {
        List<String> blockedWords = getBlockedWordsFromConfig();
        String regex = String.join("|", blockedWords); // Combine words with "OR" for regex
        return Pattern.compile("(?i)\\b(" + regex + ")\\b");
    }
}

package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.List;
import java.util.regex.Pattern;

public class SwearingChatFilter implements ChatFilter {

    //TODO when config initiates make sure to pull all words from there.
    public List<String> getBlockedWordsFromConfig() {
        return List.of("");
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
        List<String> blockedWords = getBlockedWordsFromConfig();
        String regex = String.join("|", blockedWords); // Combine words with "OR" for regex
        return Pattern.compile("(?i)\\b(" + regex + ")\\b");
    }
}

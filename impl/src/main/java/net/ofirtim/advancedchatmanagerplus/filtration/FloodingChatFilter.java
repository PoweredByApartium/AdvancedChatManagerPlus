package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.regex.Pattern;

public class FloodingChatFilter implements ChatFilter {


    @Override
    public Pattern getFilterPattern() {
        return Pattern.compile(
                "(.)\\1{4,}"                     // Repeated characters (5 or more)
                        + "|(?i)\\b(\\w+)\\b(?:\\s+\\1\\b){2,}"); // Repeated words (3 or more)
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

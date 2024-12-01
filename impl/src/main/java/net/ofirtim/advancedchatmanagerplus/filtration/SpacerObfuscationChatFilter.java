package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.regex.Pattern;

public class SpacerObfuscationChatFilter implements ChatFilter {

    @Override
    public Pattern getFilterPattern() {
        return Pattern.compile("(?i)([a-z])\\s+([a-z])");
    }

    @Override
    public ChatViolation getRelatedChatViolation() {
        return ChatViolation.SPACER_OBFUSCATION;
    }

    @Override
    public String deobfuscate(String possiblyObfuscated) {
        return possiblyObfuscated.replaceAll("(?i)([a-z])\\s+([a-z])", "$1$2");
    }
}

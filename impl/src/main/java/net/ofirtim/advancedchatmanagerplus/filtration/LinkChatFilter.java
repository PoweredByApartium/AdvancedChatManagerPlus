package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.regex.Pattern;

public class LinkChatFilter implements ChatFilter {

    @Override
    public Pattern getFilterPattern() {
        return Pattern.compile(
                "((https?://)?(www\\.)?[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,}(/[a-zA-Z0-9\\-_.]*)?"  // Links
                        + "|\\b\\d{1,3}(\\.\\d{1,3}){3}\\b)"); /*IPs*/
    }

    @Override
    public ChatViolation getRelatedChatViolation() {
        return ChatViolation.ADDRESS;
    }

    @Override
    public String deobfuscate(String possiblyObfuscated) {
        return possiblyObfuscated
                .replaceAll("\\(\\s*dot\\s*\\)", ".")   // Normalize "(dot)"
                .replaceAll("\\[\\s*dot\\s*\\]", ".")   // Normalize "[dot]"
                .replaceAll("\\{\\s*dot\\s*\\}", ".")   // Normalize "{dot}"
                .replaceAll("\\(\\s*\\.\\s*\\)", ".")   // Normalize "( . )"
                .replaceAll("\\s*\\.\\s*", ".")         // Remove spaces around dots
                .replaceAll("\\s+", "")                 // Remove all spaces
                .toLowerCase();
    }
}

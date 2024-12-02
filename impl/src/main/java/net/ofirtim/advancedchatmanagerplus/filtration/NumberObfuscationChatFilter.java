package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.regex.Pattern;

public class NumberObfuscationChatFilter implements ChatFilter {

    @Override
    public Pattern getFilterPattern() {
        return Pattern.compile("[0-9]");
    }

    @Override
    public ChatViolation getRelatedChatViolation() {
        return ChatViolation.NUMBER_OBFUSCATION;
    }

    @Override
    public String deobfuscate(String possiblyObfuscated) {
        return possiblyObfuscated
                .replace("1", "i")
                .replace("2", "z")
                .replace("3", "e")
                .replace("4", "a")
                .replace("5", "s")
                .replace("6", "g")
                .replace("7", "t")
                .replace("8", "b")
                .replace("9", "g")
                .replace("0", "o");
    }
}

package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.regex.Pattern;

public class SymbolObfuscationChatFilter implements ChatFilter {

    @Override
    public Pattern getFilterPattern() {
        return Pattern.compile("(?i)(?=.*[a-z])(?=.*[!@#$%^&*()_+\\[\\]{}|~])[a-z!@#$%^&*()_+\\[\\]{}|~]+");
    }

    @Override
    public ChatViolation getRelatedChatViolation() {
        return ChatViolation.SYMBOL_OBFUSCATION;
    }

    @Override
    public String deobfuscate(String possiblyObfuscated) {
        return possiblyObfuscated
                .replace("@", "a")
                .replace("!", "i")
                .replace("#", "h")
                .replace("$", "s")
                .replace("%", "o")
                .replace("^", "v")
                .replace("&", "e")
                .replace("*", "x")
                .replace("(", "c")
                .replace(")", "c")
                .replace("[", "i")
                .replace("]", "i")
                .replace("{", "o")
                .replace("}", "o")
                .replace("_", "")
                .replace("-", "")
                .replace("+", "t")
                .replace("~", "n");
    }
}

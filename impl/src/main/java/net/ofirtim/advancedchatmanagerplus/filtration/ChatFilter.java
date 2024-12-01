package net.ofirtim.advancedchatmanagerplus.filtration;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Deprecated(since = "https://discord.com/channels/@me/423924558404517908/1312735633453744199")
public class ChatFilter {

    private static final Pattern LINK_PATTERN = Pattern.compile("((https?://)?(www\\.)?[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,}(/[a-zA-Z0-9\\-_.]*)?|\\b\\d{1,3}(\\.\\d{1,3}){3}\\b)");


    private final boolean allowLinks;
    private final Set<ObfuscationCheckers> checkers;
    private final Set<String> blacklistedWords;

    public static FilterBuilder builder() {
        return new FilterBuilder();
    }

    public ChatFilter(final boolean allowLinks, final Set<ObfuscationCheckers> checkers, final Set<String> blacklistedWords) {
        this.allowLinks = allowLinks;
        this.checkers = checkers;
        this.blacklistedWords = blacklistedWords;
    }

    public Set<ChatViolations> getViolations(Component component) {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        String asString = miniMessage.stripTags(miniMessage.serialize(component)).toLowerCase();
        Set<net.ofirtim.advancedchatmanagerplus.ChatFilter.ChatViolation> violations = new HashSet<>();

        //Localized block for links
        if (!allowLinks) {
            String linkDeob = deobfuscatePossibleLinks(asString);
            if (LINK_PATTERN.matcher(linkDeob).find());
        }

        //Was never finished, moved to inheritance.
        for (String badWord : blacklistedWords) {
            String collector = badWord;
            if (checkers.contains(ObfuscationCheckers.NUMBERS)) {
                String deobfuscated = deobfuscateCommonNumbersToLetters(collector);

                if (deobfuscated.contains(badWord));
                collector = deobfuscated;
            }

            if (checkers.contains(ObfuscationCheckers.SYMBOLS)) {
                String deobfuscated = deobfuscateCommonSymbolsToLetters(collector);

                if (deobfuscated.contains(badWord));
                collector = deobfuscated;
            }

            if (checkers.contains(ObfuscationCheckers.SPACERS)) {
                String deobfuscated = deobfuscateSpacesAndTabsToWholeWord(collector);

                if (deobfuscated.contains(badWord));
                collector = deobfuscated;
            }

        }

        return violations;
    }

    public boolean checkFull(Component component) {
        return true;
    }

    public String deobfuscatePossibleLinks(String asString) {
        return asString.replaceAll("\\(\\s*dot\\s*\\)", ".")   // Normalize "(dot)"
                .replaceAll("\\[\\s*dot\\s*\\]", ".")   // Normalize "[dot]"
                .replaceAll("\\{\\s*dot\\s*\\}", ".")   // Normalize "{dot}"
                .replaceAll("\\(\\s*\\.\\s*\\)", ".")   // Normalize "( . )"
                .replaceAll("\\s*\\.\\s*", ".")         // Remove spaces around dots
                .replaceAll("\\s+", "")                 // Remove all spaces
                .toLowerCase();
    }

    public String deobfuscateSpacesAndTabsToWholeWord(String asString) {
        return asString
                .replaceAll("\\s+", " ")
                .replaceAll("(?i)([a-z])\\s+([a-z])", "$1$2");
    }

    public String deobfuscateCommonSymbolsToLetters(String asString) {
        //Removes existing tags if the bypass machinisim did not manage to delete any or some.
        return MiniMessage.miniMessage().stripTags(asString)
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
                .replace("{", "o")
                .replace("}", "o")
                .replace("[", "i")
                .replace("]", "i")
                .replace("|", "i")
                .replace("~", "n")
                .replace(".", "")
                .replace("_", "")
                .replace("-", "")
                .replace("+", "t")
                .toLowerCase();
    }

    public String deobfuscateCommonNumbersToLetters(String asString) {
        //Removes existing tags if the bypass machinisim did not manage to delete any or some.
        asString = MiniMessage.miniMessage().stripTags(asString);
        asString = asString
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
        return asString;
    }

    public enum ObfuscationCheckers {
        NUMBERS, SYMBOLS, SPACERS
    }

    public enum ChatViolations {
        LINK,
        NUMBER_OBFUSCATION,
        SYMBOL_OBFUSCATION,
        SPACER_OBFUSCATION,
        SWEARING,
    }

    public static class FilterBuilder {

        private boolean allowLinks;
        private final Set<ObfuscationCheckers> checkers = new HashSet<>();
        private final Set<String> blacklistedWords = new HashSet<>();

        public FilterBuilder allowLinks(final boolean allowLinks) {
            this.allowLinks = allowLinks;
            return this;
        }

        public FilterBuilder checkers(final ObfuscationCheckers... checkers) {
            this.checkers.addAll(Arrays.asList(checkers));
            return this;
        }

        public FilterBuilder blacklistedWords(final String... blacklistedWords) {
            this.blacklistedWords.addAll(Arrays.asList(blacklistedWords));
            return this;
        }

        public ChatFilter build() {
            return new ChatFilter(allowLinks, checkers, blacklistedWords);
        }
    }
}

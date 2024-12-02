package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SymbolObfuscationChatFilter implements ChatFilter {

    private static final Pattern SYMBOL_PATTERN = Pattern.compile("[!@#$%^&*()_+\\[\\]{}|~]");

    @Override
    public Pattern getFilterPattern() {
        return SYMBOL_PATTERN;
    }

    @Override
    public ChatViolation getRelatedChatViolation() {
        return ChatViolation.SYMBOL_OBFUSCATION;
    }

    @Override
    public String deobfuscate(String possiblyObfuscated) {
        StringBuilder result = new StringBuilder();
        String[] words = possiblyObfuscated.split(" ");

        for (String word : words) {
            if (isMathExpression(word)) {
                result.append(word).append(" ");
            } else {
                String cleanedWord = replaceSymbolsInWord(word);
                result.append(cleanedWord).append(" ");
            }
        }

        return result.toString().trim();
    }

    private String replaceSymbolsInWord(String word) {
        Map<Character, String> replacements = getCharacterStringMap();
        String coreWord = word.replaceAll("[!?.]+$", "");
        String punctuation = word.substring(coreWord.length());

        if (isNumericGroup(coreWord)) {
            return word;
        }

        StringBuilder processedCore = new StringBuilder();
        boolean replaced = false;

        for (char c : coreWord.toCharArray()) {
            if (replacements.containsKey(c)) {
                processedCore.append(replacements.get(c));
                replaced = true;
            } else {
                processedCore.append(c);
            }
        }

        String finalCoreWord = processedCore.toString();
        if (replaced && isValidWord(finalCoreWord)) {
            return finalCoreWord + punctuation;
        } else {
            return word;
        }
    }

    private boolean isNumericGroup(String word) {
        return word.matches("[()\\d\\s]*");
    }

    private boolean isValidWord(String word) {
        return word.matches(".*[a-zA-Z0-9].*");
    }

    private boolean isMathExpression(String word) {
        return word.matches("\\d+[+\\-*/^=]\\d+.*");
    }

    private Map<Character, String> getCharacterStringMap() {
        Map<Character, String> replacements = new HashMap<>();
        replacements.put('@', "a");
        replacements.put('!', "i");
        replacements.put('$', "s");
        replacements.put('~', "n");
        replacements.put('#', "h");
        replacements.put('%', "o");
        replacements.put('^', "v");
        replacements.put('&', "e");
        replacements.put('*', "x");
        replacements.put('(', "c");
        replacements.put(')', "c");
        replacements.put('[', "i");
        replacements.put(']', "i");
        replacements.put('{', "o");
        replacements.put('}', "o");
        replacements.put('+', "t");
        return replacements;
    }
}

package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class SymbolObfuscationChatFilter implements ChatFilter {

    @Override
    public EnumMap<ChatViolation, Integer> getViolations(String message) {
        // Initialize the violations map with the related chat violation
        EnumMap<ChatViolation, Integer> violations = new EnumMap<>(Map.of(getRelatedChatViolation(), 0));

        // Split the message into words
        String[] words = message.split("\\s+");

        for (String word : words) {
            // Skip valid math expressions and numeric groups
            if (isMathExpression(word) || isNumericGroup(word)) {
                continue;
            }

            // Deobfuscate the word
            String deobfuscated = deobfuscate(word);

            // Compare each character in the word to its deobfuscated version
            int wordViolations = 0;
            for (int i = 0; i < word.length(); i++) {
                char originalChar = word.charAt(i);
                char deobfuscatedChar = i < deobfuscated.length() ? deobfuscated.charAt(i) : originalChar;

                // Count a violation if the character differs
                if (originalChar != deobfuscatedChar) {
                    wordViolations++;
                }
            }

            // Add the violations for this word to the total count
            violations.put(getRelatedChatViolation(), violations.get(getRelatedChatViolation()) + wordViolations);
        }

        return violations;
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
            // Skip processing if the segment contains no letters
            if (!containsLetters(word)) {
                result.append(word).append(" ");
                continue;
            }

            // Skip processing if brackets exist at the start and/or end of the segment
            if (hasOuterBrackets(word)) {
                // Remove the outer brackets temporarily
                char openingBracket = word.charAt(0);
                char closingBracket = word.charAt(word.length() - 1);
                String innerContent = word.substring(1, word.length() - 1);

                // Deobfuscate the inner content
                String deobfuscatedInner = replaceSymbolsInWord(innerContent);

                // Reconstruct the segment with brackets
                String reconstructed = openingBracket + deobfuscatedInner + closingBracket;

                result.append(reconstructed).append(" ");
                continue;
            }

            // Skip processing if it's a math expression
            if (isMathExpression(word)) {
                result.append(word).append(" ");
                continue;
            }

            // Process and deobfuscate the word
            String cleanedWord = replaceSymbolsInWord(word);
            result.append(cleanedWord).append(" ");
        }

        return result.toString().trim();
    }

    public boolean hasOuterBrackets(String segment) {
        // Check for brackets at the start or end
        boolean startsWithBracket = segment.matches("^[({\\[<].*");
        boolean endsWithBracket = segment.matches(".*[)}>\\]]$");

        // If either condition is true, return true
        return startsWithBracket || endsWithBracket;
    }

    public boolean containsLetters(String segment) {
        // Returns true if the segment contains at least one alphabetic character
        return segment.matches(".*[a-zA-Z]+.*");
    }

    public String replaceSymbolsInWord(String word) {
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

    public boolean isNumericGroup(String word) {
        return word.matches("[()\\d\\s]*");
    }

    public boolean isValidWord(String word) {
        return word.matches(".*[a-zA-Z0-9].*");
    }

    public boolean isMathExpression(String word) {
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

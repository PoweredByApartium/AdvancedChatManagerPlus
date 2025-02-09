package net.ofirtim.advancedchatmanagerplus.filtration;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Pattern;

public class NumberObfuscationChatFilter implements ChatFilter {

    @Override
    public EnumMap<ChatViolation, Integer> getViolations(String message) {
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

            // Compare each character in the word with its deobfuscated version
            int letterViolations = 0;
            for (int i = 0; i < word.length(); i++) {
                char originalChar = word.charAt(i);
                char deobfuscatedChar = i < deobfuscated.length() ? deobfuscated.charAt(i) : originalChar;

                // Count a violation if the character differs
                if (originalChar != deobfuscatedChar) {
                    letterViolations++;
                }
            }

            // Add the violations for this word to the total count
            violations.put(getRelatedChatViolation(), violations.get(getRelatedChatViolation()) + letterViolations);
        }

        return violations;
    }

    @Override
    public ChatViolation getRelatedChatViolation() {
        return ChatViolation.NUMBER_OBFUSCATION;
    }

    @Override
    public String deobfuscate(String possiblyObfuscated) {
        StringBuilder result = new StringBuilder();
        String[] words = possiblyObfuscated.split(" ");

        for (String word : words) {
            // Preserve math expressions
            if (isMathExpression(word)) {
                result.append(word).append(" ");
                continue;
            }

            // Deobfuscate non-math words
            StringBuilder cleanedWord = new StringBuilder();
            String[] segments = word.split("(?<=\\W)|(?=\\W)"); // Split into letters, digits, or symbols

            for (String segment : segments) {
                if (isNumericGroup(segment) || isMathExpression(segment)) {
                    cleanedWord.append(segment); // Preserve numbers and math
                } else {
                    cleanedWord.append(deobfuscateWord(segment)); // Deobfuscate
                }
            }

            result.append(cleanedWord).append(" ");
        }

        return result.toString().trim();
    }

    private boolean isMathExpression(String word) {
        try {
            Expression expression = new ExpressionBuilder(word).build();
            expression.evaluate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isNumericGroup(String word) {
        return word.matches("\\b\\d+([.,]\\d+)?\\b");
    }

    private String deobfuscateWord(String word) {
        return word
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

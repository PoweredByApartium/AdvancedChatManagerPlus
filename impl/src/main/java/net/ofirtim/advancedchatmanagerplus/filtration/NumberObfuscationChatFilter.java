package net.ofirtim.advancedchatmanagerplus.filtration;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Pattern;

public class NumberObfuscationChatFilter implements ChatFilter {

    @Override
    public Pattern getFilterPattern() {
        // Match obfuscated digits embedded in words
        String obfuscatedDigits = "\\b[a-zA-Z]*[0-9]+[a-zA-Z]*\\b";

        // Exclude valid math expressions
        String mathPattern = "\\b\\d+([+\\-*/^]\\d+)*(=\\d+)?\\b";

        // Exclude valid numeric groups
        String numericGroupPattern = "\\b\\d+([.,]\\d+)?\\b";

        // Combine patterns using negative lookaheads to exclude math and numeric groups
        String combinedPattern = "(?!" + mathPattern + "|" + numericGroupPattern + ")" + obfuscatedDigits;

        return Pattern.compile(combinedPattern, Pattern.CASE_INSENSITIVE);
    }

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
            if (isMathExpression(word)) {
                result.append(word).append(" "); // Preserve valid math expressions
            } else if (isNumericGroup(word)) {
                result.append(word).append(" "); // Preserve valid numeric groups
            } else {
                // Deobfuscate numbers based on the replacements
                String cleanedWord = deobfuscateWord(word);
                result.append(cleanedWord).append(" ");
            }
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

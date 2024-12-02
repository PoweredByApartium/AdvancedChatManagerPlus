package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressObfuscationChatFilter implements ChatFilter {

    @Override
    public Pattern getFilterPattern() {
        String obfuscatedDot = "\\(dot\\)|\\[dot\\]|\\s+dot\\s+";
        String obfuscatedAt = "\\(at\\)|\\[at\\]|\\s+at\\s+";

        // Refined patterns for links, emails, and IPs
        String linkPattern = "(https?://\\S+|www(" + obfuscatedDot + ")+[a-zA-Z0-9]+(" + obfuscatedDot + ")+(com|org|net|c0m|n3t))";
        String emailPattern = "[a-zA-Z0-9]+(" + obfuscatedAt + ")[a-zA-Z0-9@!]+(" + obfuscatedDot + ")+(com|org|net|c0m|n3t)";
        String ipPattern = "\\b\\d{1,3}(" + obfuscatedDot + ")\\d{1,3}(" + obfuscatedDot + ")\\d{1,3}(" + obfuscatedDot + ")\\d{1,3}\\b";

        // Combine patterns
        String combinedPattern = linkPattern + "|" + emailPattern + "|" + ipPattern;

        return Pattern.compile(combinedPattern, Pattern.CASE_INSENSITIVE);
    }



    @Override
    public ChatViolation getRelatedChatViolation() {
        return ChatViolation.ADDRESS;
    }

    @Override
    public EnumMap<ChatViolation, Integer> getViolations(String message) {
        EnumMap<ChatViolation, Integer> violations = new EnumMap<>(ChatViolation.class);
        violations.put(getRelatedChatViolation(), 0);

        Matcher matcher = getFilterPattern().matcher(message);

        while (matcher.find()) {
            String match = matcher.group();
            System.out.println("Detected Violation: " + match); // Log the detected violation

            // Increment the violation count
            violations.put(getRelatedChatViolation(),
                    violations.get(getRelatedChatViolation()) + 1);
        }

        System.out.println("Total Violations Detected: " + violations.get(getRelatedChatViolation()));
        return violations;
    }

    @Override
    public String deobfuscate(String possiblyObfuscated) {
        String[] words = possiblyObfuscated.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            System.out.println("Processing word: " + word); // Log the word being processed

            if (isObfuscatedLinkOrAddress(word)) {
                System.out.println(" - Detected as obfuscated address/link: " + word);
                String cleanedWord = recursivelyReplaceObfuscations(word);
                System.out.println(" - Cleaned word: " + cleanedWord);
                result.append(cleanedWord).append(" ");
            } else {
                System.out.println(" - No obfuscation detected, preserving as-is.");
                result.append(word).append(" "); // Leave general words unchanged
            }
        }

        String deobfuscatedResult = result.toString().trim();
        System.out.println("Final deobfuscated result: " + deobfuscatedResult);
        return deobfuscatedResult;
    }

    private boolean isObfuscatedLinkOrAddress(String word) {
        // Validate context: Contains "dot" or "at" in a recognizable address/link pattern
        return word.matches(".*(\\b(dot|at)\\b).*") &&
                (word.matches(".*(http|https|www|\\.com|\\.org|\\.net).*") || word.contains("@") || word.contains("(dot)"));
    }

    private String recursivelyReplaceObfuscations(String word) {
        // Map of obfuscation patterns to replacements
        Map<String, String> replacements = Map.of(
                "(dot)", ".",
                "[dot]", ".",
                " dot ", ".",
                "(at)", "@",
                "[at]", "@",
                " at ", "@"
        );

        String previousWord;
        int replacementCount = 0; // Track how many replacements were made
        do {
            previousWord = word;
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
                if (word.contains(entry.getKey())) {
                    word = word.replace(entry.getKey(), entry.getValue());
                    System.out.println("   Replaced '" + entry.getKey() + "' with '" + entry.getValue() + "'.");
                    replacementCount++;
                }
            }
        } while (!word.equals(previousWord));

        System.out.println("Total replacements made for this word: " + replacementCount);
        return word;
    }

}

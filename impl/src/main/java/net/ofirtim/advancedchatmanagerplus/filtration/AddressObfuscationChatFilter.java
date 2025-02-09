package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressObfuscationChatFilter implements ChatFilter {

    public Pattern getFilterPattern() {
        String obfuscatedDot = "\\(dot\\)|\\[dot\\]|\\s+dot\\s+";
        String obfuscatedAt = "\\(at\\)|\\[at\\]|\\s+at\\s+";

        String linkPattern = "(https?://\\S+|www(" + obfuscatedDot + ")+[a-zA-Z0-9]+(" + obfuscatedDot + ")+(com|org|net|c0m|n3t))";
        String emailPattern = "[a-zA-Z0-9]+(" + obfuscatedAt + ")[a-zA-Z0-9@!]+(" + obfuscatedDot + ")+(com|org|net|c0m|n3t)";
        String ipPattern = "\\b\\d{1,3}(" + obfuscatedDot + ")\\d{1,3}(" + obfuscatedDot + ")\\d{1,3}(" + obfuscatedDot + ")\\d{1,3}\\b";

        String combinedPattern = linkPattern + "|" + emailPattern + "|" + ipPattern;

        return Pattern.compile(combinedPattern, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public EnumMap<ChatViolation, Integer> getViolations(String message) {
        EnumMap<ChatViolation, Integer> violations = new EnumMap<>(Map.of(getRelatedChatViolation(), 0));

        Matcher matcher = getFilterPattern().matcher(message);
        while(matcher.find()) violations.put(getRelatedChatViolation(), violations.get(getRelatedChatViolation()) + 1);
        return violations;    }

    @Override
    public ChatViolation getRelatedChatViolation() {
        return ChatViolation.ADDRESS;
    }

    @Override
    public String deobfuscate(String possiblyObfuscated) {
        String[] words = possiblyObfuscated.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {

            if (isObfuscatedLinkOrAddress(word)) {
                String cleanedWord = recursivelyReplaceObfuscations(word);
                result.append(cleanedWord).append(" ");
            } else {
                result.append(word).append(" ");
            }
        }

        return result.toString().trim();
    }

    private boolean isObfuscatedLinkOrAddress(String word) {
        return word.matches(".*(\\b(dot|at)\\b).*") &&
                (word.matches(".*(http|https|www|\\.com|\\.org|\\.net).*") || word.contains("@") || word.contains("(dot)"));
    }

    private String recursivelyReplaceObfuscations(String word) {
        Map<String, String> replacements = Map.of(
                "(dot)", ".",
                "[dot]", ".",
                " dot ", ".",
                "(at)", "@",
                "[at]", "@",
                " at ", "@"
        );

        String previousWord;
        do {
            previousWord = word;
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
                if (word.contains(entry.getKey())) {
                    word = word.replace(entry.getKey(), entry.getValue());
                }
            }
        } while (!word.equals(previousWord));

        return word;
    }

}

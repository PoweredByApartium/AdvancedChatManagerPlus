package net.ofirtim.advancedchatmanagerplus.filtration;


import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
class SwearingChatFilterTest {

    SwearingChatFilter filter = new SwearingChatFilter();
    List<String> badWords = List.of("noob", "sex", "fuck");
    String input = "you fucking no%b, s3x is good!!!";

    {
        filter.getBlockedWordsFromConfig().addAll(badWords);
    }

    @Test
    void containsBadWords() {
        assertTrue(filter.getBlockedWordsFromConfig().containsAll(badWords));
    }

    @Test
    void testPatternRecognizing() {
        Map<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(input);

        assertTrue(violations.containsKey(ChatFilter.ChatViolation.SWEARING));

        assertEquals(1, violations.get(ChatFilter.ChatViolation.SWEARING));
    }

    @Test
    void testNumberObfuscationsWithSwearing() {
        String deobfuscate = new NumberObfuscationChatFilter().deobfuscate(input);
        Map<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(deobfuscate);
        assertTrue(violations.containsKey(ChatFilter.ChatViolation.SWEARING));

        assertEquals(2, violations.get(ChatFilter.ChatViolation.SWEARING));
        assertEquals("you fucking no%b, sex is good!!!", deobfuscate);
    }

    @Test
    void testSymbolObfuscationsWithSwearing() {
        String deobfuscate = new SymbolObfuscationChatFilter().deobfuscate(input);
        Map<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(deobfuscate);
        assertTrue(violations.containsKey(ChatFilter.ChatViolation.SWEARING));

        assertEquals(2, violations.get(ChatFilter.ChatViolation.SWEARING));
        assertEquals("you fucking noob, s3x is good!!!", deobfuscate);
    }

    @Test
    void countBothObfuscationsWithSwearing() {
        input = new NumberObfuscationChatFilter().deobfuscate(input);
        input = new SymbolObfuscationChatFilter().deobfuscate(input);

        Map<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(input);
        assertTrue(violations.containsKey(ChatFilter.ChatViolation.SWEARING));

        assertEquals(3, violations.get(ChatFilter.ChatViolation.SWEARING));
        assertEquals("you fucking noob, sex is good!!!", input);
    }
}

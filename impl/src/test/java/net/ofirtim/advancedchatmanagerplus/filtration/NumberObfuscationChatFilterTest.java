package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberObfuscationChatFilterTest {

    NumberObfuscationChatFilter filter = new NumberObfuscationChatFilter();

    @Test
    void testObfuscation() {
        String input = "Hell0 m47e how are you";

        input = filter.deobfuscate(input);

        assertEquals("Hello mate how are you", input);
    }

    @Test
    void testPatternRecognizing() {
        String input = "Hell0 m47e how are you";
        Map<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(input);

        assertTrue(violations.containsKey(filter.getRelatedChatViolation()));

        assertEquals(3, violations.get(filter.getRelatedChatViolation()));
    }
}

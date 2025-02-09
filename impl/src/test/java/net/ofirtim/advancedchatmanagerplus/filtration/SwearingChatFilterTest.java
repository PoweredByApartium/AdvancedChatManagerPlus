package net.ofirtim.advancedchatmanagerplus.filtration;


import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SwearingChatFilterTest implements ChatFilterTest {

    @Override
    public ChatFilter getFilter() {
        return getChatManager().SWEAR_FILTER;
    }

    @Override
    public Stream<TestCase> getTestCases() {
        return Stream.of(
                // Test Case 1: Single Bad Word
                new TestCase("You're such a noob!", "You're such a noob!",
                        Map.of(ChatFilter.ChatViolation.SWEARING, 1)),

                // Test Case 2: Multiple Bad Words
                new TestCase("This noob is a dick!", "This noob is a dick!",
                        Map.of(ChatFilter.ChatViolation.SWEARING, 2)),

                // Test Case 3: Obfuscated Bad Word (Numbers)
                new TestCase("You're such a n00b!", "You're such a noob!",
                        Map.of(ChatFilter.ChatViolation.SWEARING, 1)),

                // Test Case 4: Obfuscated Bad Word (Symbols)
                new TestCase("Stop being such an @sshole!", "Stop being such an asshole!",
                        Map.of(ChatFilter.ChatViolation.SWEARING, 1)),

                // Test Case 5: Mixed Obfuscation (Numbers and Symbols)
                new TestCase("You're such a @$$hole!", "You're such a asshole!",
                        Map.of(ChatFilter.ChatViolation.SWEARING, 1)),

                // Test Case 6: Complex Obfuscation
                new TestCase("F4gg0t and D1ck", "Faggot and Dick",
                        Map.of(ChatFilter.ChatViolation.SWEARING, 2)),

                // Test Case 7: Repeated Obfuscated Words
                new TestCase("Sh1t, sh1t, sh1t everywhere!", "Shit, shit, shit everywhere!",
                        Map.of(ChatFilter.ChatViolation.SWEARING, 3)),

                // Test Case 8: Clean Input
                new TestCase("This is a clean sentence with no violations.",
                        "This is a clean sentence with no violations.",
                        Map.of()),

                // Test Case 9: Obfuscated Bad Word in Brackets
                new TestCase("(fuck) (sh!t)", "(fuck) (shit)",
                        Map.of(ChatFilter.ChatViolation.SWEARING, 2)),

                // Test Case 10: Complex Sentence with Obfuscation
                new TestCase("You fucking n00b, st0p be!ng such an @sshole!",
                        "You fucking noob, stop being such an asshole!",
                        Map.of(ChatFilter.ChatViolation.SWEARING, 3))
        );
    }

    @ParameterizedTest
    @MethodSource("getTestCases")
    @Override
    public void testDeobfuscationAndViolations(TestCase testCase) {
        String symbolDeobfuscation = getChatManager().SYMBOL_FILTER.deobfuscate(testCase.input());
        String numberAndSymbolDeobfuscation = getChatManager().NUMBER_FILTER.deobfuscate(symbolDeobfuscation);
        assertEquals(testCase.expectedOutput(), numberAndSymbolDeobfuscation, "Deobfuscation output mismatch");

        Map<ChatFilter.ChatViolation, Integer> actualViolations = getFilter().getViolations(numberAndSymbolDeobfuscation).get(getFilter().getRelatedChatViolation()) == 0
                ? Collections.emptyMap()
                : getFilter().getViolations(numberAndSymbolDeobfuscation);
        assertEquals(testCase.expectedViolations(), actualViolations, "Violation counts mismatch");
    }
}

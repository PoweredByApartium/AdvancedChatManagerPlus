package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.*;
class NumberObfuscationChatFilterTest implements ChatFilterTest {

    @Override
    public ChatFilter getFilter() {
        return getChatManager().NUMBER_FILTER;
    }

    @Override
    public String getInput() {
        return "Hell0 ma7e h0w are you 445511 5+5+5=10";
    }

    @Override
    public String getExpectedOutput() {
        return "Hello mate how are you 445511 5+5+5=10";
    }

    @Override
    public int getExpectedViolations() {
        return 3;
    }

    @Test
    void testMathExpressionAndNumericGroupSkipping() {
        NumberObfuscationChatFilter filter = new NumberObfuscationChatFilter();

        String input = "1234 5+5+5=10 678.90";
        String expectedOutput = "1234 5+5+5=10 678.90";

        String deobfuscated = filter.deobfuscate(input);
        assertEquals(expectedOutput, deobfuscated);

        EnumMap<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(input);
        assertEquals(0, violations.get(ChatFilter.ChatViolation.NUMBER_OBFUSCATION).intValue());
    }

    @Test
    void testDeobfuscatedShorterThanWord() {
        NumberObfuscationChatFilter filter = new NumberObfuscationChatFilter();

        String input = "H3y";
        String expectedOutput = "Hey";

        String deobfuscated = filter.deobfuscate(input);
        assertEquals(expectedOutput, deobfuscated);

        EnumMap<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(input);
        assertEquals(1, violations.get(ChatFilter.ChatViolation.NUMBER_OBFUSCATION).intValue());
    }

    @Test
    void testSegmentsContainingMathAndNumericGroups() {
        NumberObfuscationChatFilter filter = new NumberObfuscationChatFilter();

        String input = "H3y 123+456 123";
        String expectedOutput = "Hey 123+456 123";

        String deobfuscated = filter.deobfuscate(input);
        assertEquals(expectedOutput, deobfuscated);

        EnumMap<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(input);
        assertEquals(1, violations.get(ChatFilter.ChatViolation.NUMBER_OBFUSCATION).intValue());
    }

    @Test
    void testMixedInputWithMathAndObfuscations() {
        NumberObfuscationChatFilter filter = new NumberObfuscationChatFilter();

        String input = "H3y m4th 5+5 456";
        String expectedOutput = "Hey math 5+5 456";

        String deobfuscated = filter.deobfuscate(input);
        assertEquals(expectedOutput, deobfuscated);

        EnumMap<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(input);
        assertEquals(2, violations.get(ChatFilter.ChatViolation.NUMBER_OBFUSCATION).intValue());
    }

}

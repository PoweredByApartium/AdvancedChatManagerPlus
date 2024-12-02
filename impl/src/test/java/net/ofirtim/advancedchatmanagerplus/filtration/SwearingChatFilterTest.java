package net.ofirtim.advancedchatmanagerplus.filtration;


import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SwearingChatFilterTest implements ChatFilterTest {

    ChatFilter filter = getFilter();

    @Override
    public ChatFilter getFilter() {
        return getChatManager().SWEAR_FILTER;
    }

    @Override
    public String getInput() {
        return "you fucking no%b, s3x is good!!!";
    }

    @Override
    public String getExpectedOutput() {
        return getInput();
    }

    @Override
    public int getExpectedViolations() {
        return 1;
    }

    @Test
    void containsBadWords() {
        assertInstanceOf(SwearingChatFilter.class, filter);
        SwearingChatFilter swearingFilter = new SwearingChatFilter();
        assertTrue(swearingFilter.getBlockedWords().containsAll(List.of("noob", "sex", "fuck")));
    }

    @Test
    @Override
    public void testFilterViolationCounter() {
        Map<ChatFilter.ChatViolation, Integer> violations = getFilter().getViolations(getInput());
        System.out.println(violations);
        assertTrue(violations.containsKey(getFilter().getRelatedChatViolation()));

        assertEquals(getExpectedViolations(), violations.get(getFilter().getRelatedChatViolation()));
    }

    @Test
    void testFilterViolationCounterWithNumberDeobfuscation() {
        String deobfuscate = new NumberObfuscationChatFilter().deobfuscate(getInput());
        Map<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(deobfuscate);
        assertTrue(violations.containsKey(ChatFilter.ChatViolation.SWEARING));

        assertEquals(2, violations.get(ChatFilter.ChatViolation.SWEARING));
        assertEquals("you fucking no%b, sex is good!!!", deobfuscate);
    }

    @Test
    void testFilterViolationCounterWithSymbolDeobfuscation() {
        String deobfuscate = new SymbolObfuscationChatFilter().deobfuscate(getInput());
        Map<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(deobfuscate);
        assertTrue(violations.containsKey(ChatFilter.ChatViolation.SWEARING));

        assertEquals(2, violations.get(ChatFilter.ChatViolation.SWEARING));
        assertEquals("you fucking noob, s3x is good!!!", deobfuscate);
    }

    @Test
    void testFilterViolationCounterWithNumberAndSymbolDeobfuscation() {
        String deobfuscated = getChatManager().SYMBOL_FILTER.deobfuscate(getChatManager().NUMBER_FILTER.deobfuscate(getInput()));

        Map<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(deobfuscated);
        assertTrue(violations.containsKey(ChatFilter.ChatViolation.SWEARING));

        assertEquals(3, violations.get(ChatFilter.ChatViolation.SWEARING));
        assertEquals("you fucking noob, sex is good!!!", deobfuscated);
    }
}

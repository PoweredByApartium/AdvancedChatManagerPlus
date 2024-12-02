package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SymbolObfuscationChatFilterTest {

    SymbolObfuscationChatFilter filter = new SymbolObfuscationChatFilter();

    @Test
    void testSlidingWindowDeobfuscation() {
        //Uses Minecraft's 265 max chars for 1 message.
        String input = "H3ll0! Th!$ 1$ @ t3$t m3$$@g3 w!th numb3r$ (12345) @nd symb0l$!!! C@n y0u h@ndl3 th!$" +
                " c0mpl3x p!3c3 0f t3xt??? Sh@ll w3 $33 h0w f@st 1t w0rks? A$$h0l3s m@y try 2 br34k 1t, s0 b3 pr3p@r3d!!! ---";

        String output = filter.deobfuscate(input);
        assertEquals("H3ll0! This 1s a t3st m3ssag3 with numb3rs (12345) and symb0ls!!! Can y0u handl3 this c0mpl3x pi3c3 0f t3xt???" +
                        " Shall w3 s33 h0w fast 1t w0rks? Assh0l3s may try 2 br34k 1t, s0 b3 pr3par3d!!! ---",
                output);
    }


    @Test
    void testSymbolPatternRecognizing() {
        String input = "Sh!t, ~igga, a$$";
        Map<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(input);

        assertTrue(violations.containsKey(filter.getRelatedChatViolation()));
        System.out.println("Symbol violations: " + violations.get(filter.getRelatedChatViolation()));
        assertEquals(4, violations.get(filter.getRelatedChatViolation()));

    }
}

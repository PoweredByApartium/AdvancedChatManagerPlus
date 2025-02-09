package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.Map;
import java.util.stream.Stream;

class SymbolObfuscationChatFilterTest implements ChatFilterTest {

    @Override
    public ChatFilter getFilter() {
        return getChatManager().SYMBOL_FILTER;
    }

    @Override
    public Stream<TestCase> getTestCases() {
        return Stream.of(
                // Test Case 1: Basic Symbol Replacement
                // Replaces symbols commonly used for letters.
                new TestCase("H3ll0 W0rld!", "H3ll0 W0rld!",
                        Map.of()),

                // Test Case 2: Symbols in Mixed Content
                // Handles symbols embedded in regular text.
                new TestCase("W3lc0m3 t0 th3 ch@t!", "W3lc0m3 t0 th3 chat!",
                        Map.of(ChatFilter.ChatViolation.SYMBOL_OBFUSCATION, 1)),

                // Test Case 3: Preserving Outer Brackets
                // Ensures brackets are preserved while deobfuscating the inner content.
                new TestCase("(th!$) [W3lc0m3] {t3$t.}", "(this) [W3lc0m3] {t3st.}",
                        Map.of(ChatFilter.ChatViolation.SYMBOL_OBFUSCATION, 3)),

                // Test Case 4: Repeated Symbols
                // Tests a sequence of repeated symbols and ensures no false positives.
                new TestCase("@@@ $$$ ###", "@@@ $$$ ###",
                        Map.of()),

                // Test Case 5: Mixed Obfuscation with Numbers
                // Handles both numbers and symbols in the same segment.
                new TestCase("Th!s !$ @w3s0m3!", "This !$ aw3s0m3!",
                        Map.of(ChatFilter.ChatViolation.SYMBOL_OBFUSCATION, 2)),

                // Test Case 6: Complex Sentence
                // Tests a more realistic sentence with various obfuscations.
                new TestCase("H3y, h0w @r3 y0u d01ng?", "H3y, h0w ar3 y0u d01ng?",
                        Map.of(ChatFilter.ChatViolation.SYMBOL_OBFUSCATION, 1)),

                // Test Case 7: Edge Case - No Obfuscation
                // A clean sentence with no obfuscation should not trigger any violations.
                new TestCase("This is a clean sentence.", "This is a clean sentence.",
                        Map.of()),

                // Test Case 8: Mixed Symbols and Numbers in Brackets
                // Ensures symbols and numbers within brackets are processed correctly.
                new TestCase("[W3lc0m3] (t0) {th!$}", "[W3lc0m3] (t0) {this}",
                        Map.of(ChatFilter.ChatViolation.SYMBOL_OBFUSCATION, 2)),

                // Test Case 9: Symbols at the Start and End
                // Tests symbols at the edges of the text while handling inner content.
                new TestCase("!H3ll0! W3lc0m3 t0 $ymb0l$", "iH3ll0! W3lc0m3 t0 symb0ls",
                        Map.of(ChatFilter.ChatViolation.SYMBOL_OBFUSCATION, 3)),

                // Test Case 10: Obfuscated Symbols with Flooding
                // Ensures obfuscated words are processed while flooding symbols are ignored.
                new TestCase("Th!$ !$ @ l0ng $$$$$ s3nt3nc3!", "This !$ @ l0ng $$$$$ s3nt3nc3!",
                        Map.of(ChatFilter.ChatViolation.SYMBOL_OBFUSCATION, 2))
        );
    }

}
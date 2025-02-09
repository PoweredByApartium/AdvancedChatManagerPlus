package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.Map;
import java.util.stream.Stream;

class FloodingChatFilterTest implements ChatFilterTest {


    @Override
    public ChatFilter getFilter() {
        return getChatManager().FLOODING_FILTER;
    }

    @Override
    public Stream<TestCase> getTestCases() {
        return Stream.of(
                // Test Case 1: Repeated Symbols (Threshold: 5)
                // Repeated symbols (more than 5) should trigger one flooding violation.
                new TestCase("!!!!!! $$$$$$ #####", "!!!!!! $$$$$$ #####",
                        Map.of(ChatFilter.ChatViolation.FLOODING, 3)),

                // Test Case 2: Repeated Numbers (Threshold: 5)
                // Repeated numeric sequences (more than 5) should count as flooding violations.
                new TestCase("111111 222222 333333", "111111 222222 333333",
                        Map.of(ChatFilter.ChatViolation.FLOODING, 3)),

                // Test Case 3: Repeated Letters (Threshold: 7)
                // Flooding for repeated letters (more than 7) should trigger violations.
                new TestCase("aaaaaaa bbbbbbbbb", "aaaaaaa bbbbbbbbb",
                        Map.of(ChatFilter.ChatViolation.FLOODING, 2)),

                // Test Case 4: Mixed Symbols and Numbers
                // Handles flooding detection for a mix of numbers and symbols.
                new TestCase("123456 $$$$$ 7890 @@@@@@", "123456 $$$$$ 7890 @@@@@@",
                        Map.of(ChatFilter.ChatViolation.FLOODING, 2)),

                // Test Case 5: Clean Input
                // No flooding violation should occur for non-repeated content.
                new TestCase("This is a normal sentence.", "This is a normal sentence.",
                        Map.of())
        );
    }

}

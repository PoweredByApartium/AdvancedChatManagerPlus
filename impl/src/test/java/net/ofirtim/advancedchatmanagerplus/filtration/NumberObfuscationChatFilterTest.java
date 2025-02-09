package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
class NumberObfuscationChatFilterTest implements ChatFilterTest {

    @Override
    public ChatFilter getFilter() {
        return getChatManager().NUMBER_FILTER;
    }

    @Override
    public Stream<TestCase> getTestCases() {
        return Stream.of(
                // Test Case 1: Basic Numeric Conversion
                new TestCase("Th3 numb3r 1s 10.", "The number is 10.",
                        Map.of(ChatFilter.ChatViolation.NUMBER_OBFUSCATION, 3)),

                // Test Case 2: Mixed Content with Numbers
                new TestCase("Th3 c0st 0f appl3s 1s $5.", "The cost of apples is $5.",
                        Map.of(ChatFilter.ChatViolation.NUMBER_OBFUSCATION, 5)),

                // Test Case 3: Numbers with Parentheses (No Violations)
                new TestCase("123 (456)", "123 (456)", Map.of()),

                // Test Case 4: Obfuscated Numbers with Symbols
                new TestCase("Th3 ph0n3 numb3r 1s 555-1@23.", "The phone number is 555-1@23.",
                        Map.of(ChatFilter.ChatViolation.NUMBER_OBFUSCATION, 5)),

                // Test Case 5: Edge Case - Pure Numbers (No Violations)
                new TestCase("12345", "12345", Map.of()),

                // Test Case 6: Mixed Obfuscations (Numbers and Symbols)
                new TestCase("0n3 appl3 c0st5 $1!", "one apple costs $1!",
                        Map.of(ChatFilter.ChatViolation.NUMBER_OBFUSCATION, 5)),

                // Test Case 7: Math Expressions (No Violations)
                new TestCase("5+5=10", "5+5=10", Map.of()),

                // Test Case 8: Numbers Embedded in Words
                new TestCase("T3xt c0nta1n1ng numb3rs.", "Text containing numbers.",
                        Map.of(ChatFilter.ChatViolation.NUMBER_OBFUSCATION, 5)),

                // Test Case 9: Long Obfuscated Sentence
                new TestCase("Th3 5up3r l0ng numb3r 15 r3ally hard t0 gu3ss.",
                        "The super long number 15 really hard to guess.",
                        Map.of(ChatFilter.ChatViolation.NUMBER_OBFUSCATION, 8)),

                // Test Case 10: Empty Input
                new TestCase("", "", Map.of())
        );
    }
}

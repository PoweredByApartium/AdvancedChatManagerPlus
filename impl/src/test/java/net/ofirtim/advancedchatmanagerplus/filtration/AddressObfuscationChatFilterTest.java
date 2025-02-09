package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressObfuscationChatFilterTest implements ChatFilterTest {

    @Override
    public ChatFilter getFilter() {
        return getChatManager().ADDRESS_FILTER;
    }

    @Override
    public Stream<TestCase> getTestCases() {
        return Stream.of(
                // Test Case 1: Standard IPv4 Address
                // A valid IPv4 address should trigger one violation.
                new TestCase("Connect to 192.168.1.1",
                        "Connect to 192.168.1.1",
                        Map.of(ChatFilter.ChatViolation.ADDRESS, 1)),

                // Test Case 2: Standard Domain
                // A valid domain should trigger one violation.
                new TestCase("Visit example.com for more info.",
                        "Visit example.com for more info.",
                        Map.of(ChatFilter.ChatViolation.ADDRESS, 1)),

                // Test Case 3: Subdomain
                // A domain with a subdomain should trigger one violation.
                new TestCase("Access docs.example.com for documentation.",
                        "Access docs.example.com for documentation.",
                        Map.of(ChatFilter.ChatViolation.ADDRESS, 1)),

                // Test Case 4: Obfuscated IPv4 with Numbers
                // Obfuscated IPv4 address using numeric replacements.
                new TestCase("C0nn3ct t0 192.168.0.1",
                        "Connect to 192.168.0.1",
                        Map.of(ChatFilter.ChatViolation.ADDRESS, 1)),

                // Test Case 5: Obfuscated Domain with Symbols
                // Domains with symbols replacing letters should be flagged.
                new TestCase("Check 3xample.c0m for details.",
                        "Check example.com for details.",
                        Map.of(ChatFilter.ChatViolation.ADDRESS, 1)),

                // Test Case 6: Multiple IPv4 Addresses
                // Multiple IPv4 addresses in the same input should each trigger violations.
                new TestCase("Ping 192.168.1.1 and 10.0.0.1 for network testing.",
                        "Ping 192.168.1.1 and 10.0.0.1 for network testing.",
                        Map.of(ChatFilter.ChatViolation.ADDRESS, 2)),

                // Test Case 7: Multiple Domains
                // Multiple domains in a single input should each trigger violations.
                new TestCase("Visit example.com and test.org for details.",
                        "Visit example.com and test.org for details.",
                        Map.of(ChatFilter.ChatViolation.ADDRESS, 2)),

                // Test Case 8: Mixed IPv4 and Domain
                // An input containing both an IP address and a domain should trigger violations for each.
                new TestCase("Server at 192.168.1.1 or example.com.",
                        "Server at 192.168.1.1 or example.com.",
                        Map.of(ChatFilter.ChatViolation.ADDRESS, 2)),

                // Test Case 9: Obfuscated IPv4 and Domain
                // A mix of obfuscated IP and domain should still be flagged correctly.
                new TestCase("Access 192.168.0.1 and 3xample.c0m",
                        "Access 192.168.0.1 and example.com",
                        Map.of(ChatFilter.ChatViolation.ADDRESS, 2)),

                // Test Case 10: Clean Input
                // An input with no IPs or domains should result in no violations.
                new TestCase("This is just a regular sentence with no addresses.",
                        "This is just a regular sentence with no addresses.",
                        Map.of())
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

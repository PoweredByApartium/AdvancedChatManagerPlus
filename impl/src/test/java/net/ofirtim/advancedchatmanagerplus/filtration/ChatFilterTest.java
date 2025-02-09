package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import net.ofirtim.advancedchatmanagerplus.state.ChatManager;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface ChatFilterTest {

    ChatFilter getFilter();

    Stream<TestCase> getTestCases();

    default ChatManager getChatManager() {
        return new ChatManager();
    }

    @ParameterizedTest
    @MethodSource("getTestCases")
    default void testDeobfuscationAndViolations(TestCase testCase) {
        String actualOutput = getFilter().deobfuscate(testCase.input());
        assertEquals(testCase.expectedOutput(), actualOutput, "Deobfuscation output mismatch");

        Map<ChatFilter.ChatViolation, Integer> actualViolations = getFilter().getViolations(testCase.input()).get(getFilter().getRelatedChatViolation()) == 0
                ? Collections.emptyMap()
                : getFilter().getViolations(testCase.input());
        assertEquals(testCase.expectedViolations(), actualViolations, "Violation counts mismatch");
    }

    record TestCase(
            String input,
            String expectedOutput,
            @Nullable Map<ChatFilter.ChatViolation, Integer> expectedViolations) {}
}

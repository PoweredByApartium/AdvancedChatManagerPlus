package net.ofirtim.advancedchatmanagerplus.filtration;

import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import net.ofirtim.advancedchatmanagerplus.state.ChatManager;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public interface ChatFilterTest {

    default ChatManager getChatManager() {
        return new ChatManager();
    }

    ChatFilter getFilter();

    String getInput();

    String getExpectedOutput();

    int getExpectedViolations();

    @Test
    default void testDeobfuscationProcess() {
        String input = getInput();
        input = getFilter().deobfuscate(input);

        assertEquals(getExpectedOutput(), input);

    }

    @Test
    default void testFilterViolationCounter() {

        Map<ChatFilter.ChatViolation, Integer> violations = getFilter().getViolations(getInput());

        assertTrue(violations.containsKey(getFilter().getRelatedChatViolation()));

        assertEquals(getExpectedViolations(), violations.get(getFilter().getRelatedChatViolation()));
    }
}

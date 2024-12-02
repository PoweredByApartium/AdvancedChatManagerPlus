package net.ofirtim.advancedchatmanagerplus.state;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import net.ofirtim.advancedchatmanagerplus.Response;
import net.ofirtim.advancedchatmanagerplus.filtration.NumberObfuscationChatFilter;
import net.ofirtim.advancedchatmanagerplus.filtration.SymbolObfuscationChatFilter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChatManagerTest {

    ChatManager chatManager = new ChatManager();

    @Test
    void testResultCompressionAllow() {
        Component component = MiniMessage.miniMessage().deserialize("Hello world! Th1s !s a nice test!");
        chatManager.activeFilters.putAll("global", List.of(new NumberObfuscationChatFilter(), new SymbolObfuscationChatFilter()));
        Response result = chatManager.checkMessage("global", component);

        assertEquals(ChatFilter.ActionResult.ALLOW, result.actionResult());
        assertEquals(2,result.violations().values().stream().mapToInt(Integer::intValue).sum());
    }

    @Test
    void testResultCompressionDeny() {
        String input = "Hello world! Th1s !s a nice test b!tch b!tch";
        Component component = MiniMessage.miniMessage().deserialize("<red>" + input + "</red>");
        System.out.println(chatManager.NUMBER_FILTER.deobfuscate(input));
        System.out.println(chatManager.NUMBER_FILTER.getViolations(input).get(chatManager.NUMBER_FILTER.getRelatedChatViolation()));

        System.out.println(chatManager.SYMBOL_FILTER.deobfuscate(input));
        System.out.println(chatManager.SYMBOL_FILTER.getViolations(input).get(chatManager.SYMBOL_FILTER.getRelatedChatViolation()));


        chatManager.activeFilters.putAll("global", List.of(chatManager.NUMBER_FILTER, chatManager.SYMBOL_FILTER));
        Response result = chatManager.checkMessage("global", component);

        assertEquals(ChatFilter.ActionResult.DENY, result.actionResult());
    }
}

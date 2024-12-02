package net.ofirtim.advancedchatmanagerplus.state;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.ofirtim.advancedchatmanagerplus.ChatFilter;
import net.ofirtim.advancedchatmanagerplus.Response;
import net.ofirtim.advancedchatmanagerplus.filtration.*;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChatManager {

    SetMultimap<String, ChatFilter> activeFilters = MultimapBuilder.hashKeys().hashSetValues().build(); /*TODO add configuration puller in the near future.*/
    private Set<ChatFilter.ChatViolation> immediateActionViolations = new HashSet<>();
    private int maxViolationsPerMessage = 7; //TODO add configuration puller in the near future

    public final ChatFilter
            SYMBOL_FILTER = new SymbolObfuscationChatFilter(),
            NUMBER_FILTER = new NumberObfuscationChatFilter(),
            SWEAR_FILTER = new SwearingChatFilter(),
            ADDRESS_FILTER = new AddressObfuscationChatFilter(),
            FLOODING_FILTER = new FloodingChatFilter();

    public Response checkMessage(String channel, Component component) {
        EnumMap<ChatFilter.ChatViolation, Integer> totalViolations = new EnumMap<>(ChatFilter.ChatViolation.class);
        MiniMessage miniMessage = MiniMessage.miniMessage();
        String serialized = miniMessage.stripTags(miniMessage.serialize(component));
        //an iteration for loop for all active filters for this chat channel.
        for (ChatFilter filter : activeFilters.get(channel)) {
            //Return all possible Violations for this filter
            EnumMap<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(serialized);
            totalViolations.putAll(violations);
            System.out.println("collected violations from " + filter.getRelatedChatViolation().name() + ": " + violations);
            //an iteration for loop for all violations as entrys
            for (Map.Entry<ChatFilter.ChatViolation, Integer> entry : violations.entrySet()) {
                if (immediateActionViolations.contains(entry.getKey())) {
                    System.out.println("Immediate Violation found!");
                    return new Response(ChatFilter.ActionResult.IMMEDIATE_ACTION, null);
                }
            }
        }

        int totalViolationsCount = totalViolations.values().stream().mapToInt(Integer::intValue).sum();

        if (totalViolationsCount >= maxViolationsPerMessage) return new Response(ChatFilter.ActionResult.DENY, totalViolations);
        return new Response(ChatFilter.ActionResult.ALLOW, totalViolations);
    }


}

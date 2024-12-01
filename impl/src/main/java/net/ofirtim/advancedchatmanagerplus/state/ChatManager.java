package net.ofirtim.advancedchatmanagerplus.state;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.ofirtim.advancedchatmanagerplus.ChatFilter;

import java.util.*;

public class ChatManager {

    private SetMultimap<String, ChatFilter> activeFilters = MultimapBuilder.hashKeys().hashSetValues().build(); /*TODO add configuration puller in the near future.*/
    private Set<ChatFilter.ChatViolation> immediateActionViolations = new HashSet<>();
    private int maxViolationsPerMessage = 0; //TODO add configuration puller in the near future


    public ChatFilter.ActionResult checkMessage(String channel, Component component) {
        int violationCounter = 0;
        String serialized = PlainTextComponentSerializer.plainText().serialize(component);

        //an iteration for loop for all active filters for this chat channel.
        for (ChatFilter filter : activeFilters.get(channel)) {
            //Return all possible Violations for this filter
            EnumMap<ChatFilter.ChatViolation, Integer> violations = filter.getViolations(serialized);

            //an iteration for loop for all violations as entrys
            for (Map.Entry<ChatFilter.ChatViolation, Integer> entry : violations.entrySet()) {
                if (immediateActionViolations.contains(entry.getKey())) return ChatFilter.ActionResult.IMMEDIATE_ACTION;

                violationCounter++;
            }

            if (violationCounter >= maxViolationsPerMessage) return ChatFilter.ActionResult.DENY;
        }
        return ChatFilter.ActionResult.ALLOW;
    }


}

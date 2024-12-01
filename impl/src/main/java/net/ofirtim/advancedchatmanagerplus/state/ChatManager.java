package net.ofirtim.advancedchatmanagerplus.state;

import net.kyori.adventure.text.Component;

import java.util.List;

public class ChatManager {

    public boolean isAllowed(Component component, List<VoigonChatFilter> filters) {
        for (VoigonChatFilter filter : filters) {
            if (!filter.check(component).allowed())
                return false;
        }
        return true;
    }

}

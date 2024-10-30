package net.ofirtim.advancedchatmanagerplus.api.structs;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class DirectMessageSession {

    private final Instant sessionStartTime;
    private Instant sessionEndTime;
    private final UUID initiator;
    private final List<Message> messages;

    public DirectMessageSession(Instant sessionStartTime, UUID initiator, List<Message> messages) {
        this.sessionStartTime = sessionStartTime;
        this.initiator = initiator;
        this.messages = messages;
    }
}

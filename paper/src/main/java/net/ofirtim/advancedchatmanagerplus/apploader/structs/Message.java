package net.ofirtim.advancedchatmanagerplus.apploader.structs;

import org.bukkit.ChatColor;

public class Message {

    public static final Message
            DEFAULT_MESSAGE = new Message("msg-default",ChatColor.BLUE + "message to load general message model.", MessageSeverity.GENERAL),
            NO_PERMISSION = new Message("no-permission", "You do not have permission to execute the following action!", MessageSeverity.ERROR);










    //------------------------------------- BOILERPLATE ----------------------------------------///
    private final String
            messageKey,
            messageContent;

    private final MessageSeverity severity;

    public Message(String messageKey, String messageContent, MessageSeverity severity) {
        this.messageKey = messageKey;
        this.messageContent = messageContent;
        this.severity = severity;
    }

    public enum MessageSeverity {
        WARNING, INFO, GENERAL, ERROR
    }

    public MessageSeverity getSeverity() {
        return severity;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public String getMessageKey() {
        return messageKey;
    }
}

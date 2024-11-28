/*
 * Copyright (c) 2024, Lior Slakman (me@voigon.dev), ALL RIGHTS RESERVED
 * Do not use, copy, modify, and/or distribute this software without explicit permission from the
 * rights holder. Reselling this product is not allowed. Transfer of the source code to any person
 * or organization not explicitly approved by the rights holder via a license agreement is hereby forbidden.
 */

package net.ofirtim.advancedchatmanagerplus;

public class Message {

    public static final Message
            DEFAULT_MESSAGE = new Message("msg-default","message to load general message model.", MessageSeverity.INFO),
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

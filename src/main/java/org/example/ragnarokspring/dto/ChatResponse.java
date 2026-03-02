package org.example.ragnarokspring.dto;

public class ChatResponse {
    private Long chatID;
    private String reply;

    public ChatResponse(Long chatID, String reply) {
        this.chatID = chatID;
        this.reply = reply;
    }
    public Long getChatID() {return chatID;}
}

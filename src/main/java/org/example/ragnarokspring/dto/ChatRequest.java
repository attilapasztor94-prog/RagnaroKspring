package org.example.ragnarokspring.dto;

public class ChatRequest {
    private Long chatID;
    private String message;

    public Long getChatID() {return chatID;}
    public void setChatID(Long chatID) {this.chatID = chatID;}
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
}

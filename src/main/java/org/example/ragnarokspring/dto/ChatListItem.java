package org.example.ragnarokspring.dto;

import java.time.Instant;

public class ChatListItem {
    private Long id;
    private String title;
    private Instant updatedAt;

    public ChatListItem(Long id, String title, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.updatedAt = updatedAt;
    }
    public Long getId() {return id;}
    public String getTitle() {return title;}
    public Instant getUpdatedAt() {return updatedAt;}
}

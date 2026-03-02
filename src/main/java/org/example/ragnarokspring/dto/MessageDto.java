package org.example.ragnarokspring.dto;

import org.example.ragnarokspring.domain.Role;
import java.time.Instant;

public class MessageDto {
    private Role role;
    private String content;
    private Instant createdAt;


    public MessageDto(Role role, String content, Instant createdAt) {
        this.role = role;
        this.content = content;
        this.createdAt = createdAt;
    }
    public Role getRole() {return role;}
    public String getContent() {return content;}
    public Instant getCreatedAt() {return createdAt;}
}
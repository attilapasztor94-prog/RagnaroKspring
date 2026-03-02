package org.example.ragnarokspring.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    private Chat chat;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Instant createdAt = Instant.now();

    public Long getId() {return ID;}
    public Chat getChat() {return chat;}
    public void setChat(Chat chat) {this.chat = chat;}
    public Role getRole() {return role;}
    public void setRole(Role role) {this.role = role;}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
    public Instant getCreatedAt() {return createdAt;}
}

package org.example.ragnarokspring.service;

import org.example.ragnarokspring.domain.Chat;
import org.example.ragnarokspring.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ConversationService {

    private final ChatRepository repo;

    public ConversationService(ChatRepository repo) {
        this.repo = repo;
    }

    public Chat getOrCreate(Long id) {
        if (id == null) {
            Chat c = new Chat();
            if (c.getTitle() == null || c.getTitle().isBlank()) {
                c.setTitle("New chat");
            }
            c.setCreatedAt(Instant.now());
            c.setUpdatedAt(Instant.now());
            return repo.save(c);
        }

        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found: " + id));
    }

    public void touch(Chat c) {
        c.setUpdatedAt(Instant.now());
        repo.save(c);
    }
}
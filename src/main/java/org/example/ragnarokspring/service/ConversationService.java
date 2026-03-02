package org.example.ragnarokspring.service;

import org.example.ragnarokspring.domain.Chat;
import org.example.ragnarokspring.repository.ChatRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
public class ConversationService {
    private final ChatRepository repo;

    public ConversationService(ChatRepository repo) { this.repo = repo; }

    public Chat getOrCreate(Long id) {
        if (id == null) {
            Chat c = new Chat();
            c.setTitle("New chat");
            c.setUpdatedAt(Instant.now());
            return repo.save(c);
        }
        return repo.findById(id).orElseThrow();
    }

    public void touch(Chat c) {
        c.setUpdatedAt(Instant.now());
        repo.save(c);
    }
}
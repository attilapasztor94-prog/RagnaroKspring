package org.example.ragnarokspring.controller;

import jakarta.validation.Valid;
import org.example.ragnarokspring.dto.ChatListItem;
import org.example.ragnarokspring.dto.ChatRequest;
import org.example.ragnarokspring.dto.ChatResponse;
import org.example.ragnarokspring.dto.MessageDto;
import org.example.ragnarokspring.repository.ChatRepository;
import org.example.ragnarokspring.repository.MessageRepository;
import org.example.ragnarokspring.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final ChatRepository chatRepository;
    private final MessageRepository messages;

    public ChatController(ChatService chatService,
                          ChatRepository chatRepository,
                          MessageRepository messages) {
        this.chatService = chatService;
        this.chatRepository = chatRepository;
        this.messages = messages;
    }

    // Send message
    @PostMapping("/send")
    public ChatResponse send(@Valid @RequestBody ChatRequest req) {
        return chatService.send(req);
    }

    // Chat list (sidebar)
    @GetMapping("/chats")
    public List<ChatListItem> chats() {
        return chatRepository.findAll().stream()
                .map(c -> new ChatListItem(
                        c.getId(),
                        c.getTitle(),
                        c.getUpdatedAt()))
                .toList();
    }

    // Chat messages
    @GetMapping("/chats/{id}/messages")
    public List<MessageDto> messages(@PathVariable Long id) {
        return messages.findByChatIdOrderByCreatedAtAsc(id).stream()
                .map(m -> new MessageDto(
                        m.getRole(),
                        m.getContent(),
                        m.getCreatedAt()))
                .toList();
    }
}
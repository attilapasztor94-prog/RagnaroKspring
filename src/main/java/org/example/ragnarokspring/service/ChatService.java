package org.example.ragnarokspring.service;

import org.example.ragnarokspring.domain.*;
import org.example.ragnarokspring.dto.*;
import org.example.ragnarokspring.repository.MessageRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatService {
    private final ConversationService conversationService;
    private final MessageRepository messageRepository;
    private final ChatClient chatClient;

    public ChatService(ConversationService conversationService,
                       MessageRepository messageRepository,
                       ChatClient chatClient) {
        this.conversationService = conversationService;
        this.messageRepository = messageRepository;
        this.chatClient = chatClient;
    }

    public ChatResponse send(ChatRequest req) {
        // 1. Preluăm/Creăm Chat-ul (folosind denumirea corectă din DTO: getChatID)
        Chat c = conversationService.getOrCreate(req.getConversationId());

        // 2. Mesaj Utilizator
        Message user = new Message();
        user.setChat(c);
        user.setRole(Role.USER);
        user.setContent(req.getMessage());
        messageRepository.save(user);

        // 3. Istoric (folosind metoda corectă din repository)
        List<Message> history = messageRepository.findByChatIdOrderByCreatedAtAsc(c.getId());

        // 4. Prompt
        StringBuilder prompt = new StringBuilder("You are Ragnarok ICS.\n");
        for (Message m : history) {
            prompt.append(m.getRole()).append(": ").append(m.getContent()).append("\n");
        }

        // 5. Apel AI
        String reply = chatClient.prompt(prompt.toString()).call().content();

        // 6. Mesaj AI
        Message ai = new Message();
        ai.setChat(c);
        ai.setRole(Role.ASSISTANT);
        ai.setContent(reply);
        messageRepository.save(ai);

        // 7. Update timestamp
        conversationService.touch(c);

        return new ChatResponse(c.getId(), reply);
    }
}
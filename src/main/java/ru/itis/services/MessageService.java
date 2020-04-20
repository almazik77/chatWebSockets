package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;
import ru.itis.repositories.MessageRepository;

import java.util.List;
import java.util.Optional;

@Component
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    public Optional<Message> find(Long id) {
        return messageRepository.findOne(id);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public void save(MessageDto messageDto) {
        Message message = Message.builder()
                .roomId(messageDto.getRoomId())
                .sender(userService.findUserByEmail(messageDto.getEmail()))
                .text(messageDto.getText())
                .build();
        messageRepository.save(message);
    }

    public List<MessageDto> findWithRoomId(String id) {
        return MessageDto.from(messageRepository.findWithRoomId(id));
    }

}

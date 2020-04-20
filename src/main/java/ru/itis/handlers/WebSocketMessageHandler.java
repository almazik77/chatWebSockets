package ru.itis.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.dto.MessageDto;
import ru.itis.services.MessageService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class WebSocketMessageHandler extends TextWebSocketHandler {

    private static final Map<String, Map<String, WebSocketSession>> rooms = new HashMap<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageService messageService;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        String messageText = (String) message.getPayload();
        MessageDto messageDto = objectMapper.readValue(messageText, MessageDto.class);

        messageService.save(messageDto);

        String roomId = messageDto.getRoomId();
        Map<String, WebSocketSession> map;

        if (rooms.containsKey(roomId)) {
            map = rooms.get(roomId);
        } else {
            map = new HashMap<>();
        }

        map.put(messageDto.getEmail(), session);
        rooms.put(roomId, map);

        for (WebSocketSession webSocketSession : map.values()) {
            webSocketSession.sendMessage(message);
        }
    }

    public Set<String> getRoomsId() {
        return rooms.keySet();
    }
}

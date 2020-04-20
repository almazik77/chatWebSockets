package ru.itis.controllers;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.MessageDto;
import ru.itis.handlers.WebSocketMessageHandler;
import ru.itis.models.Message;
import ru.itis.services.MessageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
public class ChatController {

    @Autowired
    private WebSocketMessageHandler handler;

    @Autowired
    private MessageService messageService;

    @SneakyThrows
    @GetMapping("/room/{roomId:.+}")
    public ModelAndView getChatPage(@PathVariable("roomId") String roomId,
                                    HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String email = (String) request.getSession().getAttribute("email");

        if (email == null) {
            return new ModelAndView("redirect:/signIn");
        }

        List<MessageDto> messages = messageService.findWithRoomId(roomId);

        modelAndView.addObject("email", email);
        modelAndView.addObject("roomId", roomId);
        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("chat");
        return modelAndView;
    }

    @SneakyThrows
    @GetMapping("/rooms")
    public ModelAndView getRooms(HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("email");
        System.out.println(email);

        if (email == null) {
            return new ModelAndView("redirect:/signIn");
        }

        ModelAndView modelAndView = new ModelAndView();
        Set<String> roomsId = handler.getRoomsId();
        modelAndView.addObject("roomsId", roomsId);
        modelAndView.addObject("email", email);
        modelAndView.setViewName("rooms");
        return modelAndView;
    }

}

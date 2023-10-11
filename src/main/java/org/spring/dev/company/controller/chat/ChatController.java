package org.spring.dev.company.controller.chat;

import org.spring.dev.company.entity.chat.ChatRoomEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private Map<String, ChatRoomEntity> chatRooms = new ConcurrentHashMap<>();

    @GetMapping("/chat")
    public String chat(){
        return "/chat/chat";
    }







}

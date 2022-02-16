package com.websocket.chat.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String name;
    private String ownerId;

    public static ChatRoom create(String name, String ownerId) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        chatRoom.ownerId = ownerId;
        return chatRoom;
    }
}
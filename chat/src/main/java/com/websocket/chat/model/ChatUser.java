package com.websocket.chat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatUser {
	private String roomId;
	private String userId;
	private int auth;
}

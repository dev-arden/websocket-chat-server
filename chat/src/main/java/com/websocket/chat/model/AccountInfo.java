package com.websocket.chat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountInfo {
	private String id;
	private String password;
	private String auth;
}

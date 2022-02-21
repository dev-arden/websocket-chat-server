package com.websocket.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.websocket.chat.model.ChatRoom;
import com.websocket.chat.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
	
	@Autowired
	private ChatRoomService chatroomservice;

	@GetMapping("/user")
	@ResponseBody
	public String getUserInfo() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String id = auth.getName();
		 return id;
	}

	 // 채팅 리스트 화면
	 @GetMapping("/room")
	 public String rooms(Model model) {
	     return "/chat/room";
	 }
	 // 모든 채팅방 목록 반환
	 @GetMapping("/rooms")
	 @ResponseBody
	 public List<ChatRoom> room() {
	     return chatroomservice.findAllRoom();
	 }
	 // 채팅방 생성
	 @PostMapping("/room")
	 @ResponseBody
	 public void createRoom(@RequestParam String name, @RequestParam String ownerId) {
		 chatroomservice.createRoom(name, ownerId);
	     //return chatRoomRepository.createChatRoom(name);
	 }
	 
	 // 채팅방 입장 화면
	 @GetMapping("/room/enter/{roomId}")
	 public String roomDetail(Model model, @PathVariable String roomId) {
	     model.addAttribute("roomId", roomId);
	     return "/chat/roomdetail";
	 }
	 // 특정 채팅방 조회
	 @GetMapping("/room/{roomId}")
	 @ResponseBody
	 public ChatRoom roomInfo(@PathVariable String roomId) {
	     return chatroomservice.findRoomById(roomId);
	 }
	 
	// 채팅방 입장 요청 화면
	 @GetMapping("/room/enter/{roomId}/{ownerId}")
	 public String roomPermission(Model model, @PathVariable String roomId, @PathVariable String ownerId) {
	     model.addAttribute("roomId", roomId);
	     model.addAttribute("ownerId", ownerId);
	     return "/chat/roompermission";
	 }
	 
	// 특정 채팅방 조회
	 @GetMapping("/room/{roomId}/{ownerId}")
	 @ResponseBody
	 public ChatRoom roomOwnerInfo(@PathVariable String roomId, @PathVariable String ownerId) {
	     return chatroomservice.findRoomOwnerById(roomId, ownerId);
	 }
}

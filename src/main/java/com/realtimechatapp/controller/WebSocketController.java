package com.realtimechatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.realtimechatapp.dto.ChatMessage;
import com.realtimechatapp.dto.MessageRequest;
import com.realtimechatapp.entity.Message;
import com.realtimechatapp.service.ChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private ChatService chatService;

	@MessageMapping("/private-message")
	public void sendMessage(ChatMessage chatMessage) {

	    MessageRequest request = new MessageRequest();
	    request.setSenderId(chatMessage.getSenderId());
	    request.setReceiverId(chatMessage.getReceiverId());
	    request.setContent(chatMessage.getContent());

	    Message savedMessage = chatService.sendMessage(request);

	    template.convertAndSend(
	            "/topic/user/" + chatMessage.getReceiverId(),
	            savedMessage);
	}

	// Private Chat
	@PostMapping("/send")
	public ResponseEntity<Message> send(@RequestBody MessageRequest request) {

		return ResponseEntity.ok(chatService.sendMessage(request));
	}

}

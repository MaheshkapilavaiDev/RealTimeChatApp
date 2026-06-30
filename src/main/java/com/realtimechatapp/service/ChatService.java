package com.realtimechatapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.realtimechatapp.dto.MessageRequest;
import com.realtimechatapp.entity.Message;
import com.realtimechatapp.service.ChatService;

import com.realtimechatapp.enums.MessageStatus;
import com.realtimechatapp.repository.MessageRepository;

@Service
public class ChatService {

	@Autowired
	private MessageRepository messageRepository;

	public Message sendMessage(MessageRequest request) {

		Message message = new Message();

		message.setSenderId(request.getSenderId());
		message.setReceiverId(request.getReceiverId());
		message.setGroupId(request.getGroupId()); // null for private chat
		message.setContent(request.getContent());

		message.setStatus(MessageStatus.SENT);
		message.setCreatedAt(LocalDateTime.now());

		return messageRepository.save(message);
	}

	public Message sendGroupMessage(MessageRequest request) {

		Message message = new Message();

		message.setSenderId(request.getSenderId());
		message.setGroupId(request.getGroupId());
		message.setContent(request.getContent());
		message.setStatus(MessageStatus.SENT);
		message.setCreatedAt(LocalDateTime.now());

		return messageRepository.save(message);
	}

	public List<Message> getGroupMessages(Long groupId) {

		return messageRepository.findByGroupIdOrderByCreatedAtAsc(groupId);
	}

	public Page<Message> getGroupMessages(Long groupId, int page, int size) {

		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt"));

		return messageRepository.findByGroupId(groupId, pageable);
	}

}

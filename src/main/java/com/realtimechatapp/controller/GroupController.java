package com.realtimechatapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.realtimechatapp.dto.GroupRequest;
import com.realtimechatapp.dto.GroupResponse;
import com.realtimechatapp.dto.MessageRequest;
import com.realtimechatapp.entity.Message;
import com.realtimechatapp.service.ChatService;
import com.realtimechatapp.service.GroupService;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private ChatService chatService;

	@PostMapping
	public GroupResponse createGroup(@RequestBody GroupRequest request) {
		return groupService.create(request);
	}
	
	@PostMapping("/{groupId}/members")
    public ResponseEntity<String> addMember(
            @PathVariable Long groupId,
            @RequestParam Long userId) {

        String message = groupService.addMember(groupId, userId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{groupId}/members/{userId}")
    public ResponseEntity<String> removeMember(
            @PathVariable Long groupId,
            @PathVariable Long userId) {

        String message = groupService.removeMember(groupId, userId);
        return ResponseEntity.ok(message);
    }
    
    @PostMapping("/group/send")
    public ResponseEntity<Message> sendGroupMessage(
            @RequestBody MessageRequest request) {

        return ResponseEntity.ok(chatService.sendGroupMessage(request));
    }

    @GetMapping("/groups/{groupId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable Long groupId) {

        return ResponseEntity.ok(
                chatService.getGroupMessages(groupId));
    }
    
    @GetMapping("/groups/{groupId}/messages/page")
    public ResponseEntity<Page<Message>> getMessages(
            @PathVariable Long groupId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                chatService.getGroupMessages(groupId, page, size));
    }
}
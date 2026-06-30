package com.realtimechatapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MessageRequest {

	@NotNull(message = "Sender ID is required")
	private Long senderId;

	@NotNull(message = "Receiver ID is required")
	private Long receiverId;

	@NotBlank(message = "Message content cannot be empty")
	private String content;
	
	private Long groupId;

	public Long getGroupId() {
	    return groupId;
	}

	public void setGroupId(Long groupId) {
	    this.groupId = groupId;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

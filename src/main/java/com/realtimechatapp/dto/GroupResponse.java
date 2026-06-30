package com.realtimechatapp.dto;

public class GroupResponse {

	private Long id;
	private String groupName;
	private String message;

	public GroupResponse() {
	}

	public GroupResponse(Long id, String groupName, String message) {
		this.id = id;
		this.groupName = groupName;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

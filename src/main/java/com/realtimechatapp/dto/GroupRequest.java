package com.realtimechatapp.dto;

public class GroupRequest {

	private String groupName;

	public GroupRequest() {
	}

	public GroupRequest(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}

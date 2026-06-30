package com.realtimechatapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group_members")
public class GroupMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long groupId;

	private Long userId;

	public GroupMember() {
	}

	public Long getId() {
		return id;
	}

	public Long getGroupId() {
		return groupId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}

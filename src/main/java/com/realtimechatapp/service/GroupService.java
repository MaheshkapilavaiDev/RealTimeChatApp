package com.realtimechatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realtimechatapp.dto.GroupRequest;
import com.realtimechatapp.dto.GroupResponse;
import com.realtimechatapp.entity.ChatGroup;
import com.realtimechatapp.entity.GroupMember;
import com.realtimechatapp.repository.GroupMemberRepository;
import com.realtimechatapp.repository.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private GroupMemberRepository memberRepository;

	public GroupResponse create(GroupRequest request) {

		ChatGroup group = new ChatGroup();
		group.setGroupName(request.getGroupName());

		ChatGroup savedGroup = groupRepository.save(group);

		GroupResponse response = new GroupResponse();
		response.setId(savedGroup.getId());
		response.setGroupName(savedGroup.getGroupName());
		response.setMessage("Group created successfully");

		return response;
	}
	
	public String addMember(Long groupId, Long userId) {

        GroupMember member = new GroupMember();
        member.setGroupId(groupId);
        member.setUserId(userId);

        memberRepository.save(member);

        return "Member added successfully";
    }

    public String removeMember(Long groupId, Long userId) {

        GroupMember member = memberRepository
                .findByGroupIdAndUserId(groupId, userId)
                .orElseThrow(() ->
                        new RuntimeException("Member not found"));

        memberRepository.delete(member);

        return "Member removed successfully";
    }
}

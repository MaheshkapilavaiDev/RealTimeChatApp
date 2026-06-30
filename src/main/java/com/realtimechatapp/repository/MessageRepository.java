package com.realtimechatapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realtimechatapp.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	Page<Message> findBySenderIdAndReceiverId(
            Long senderId,
            Long receiverId,
            Pageable pageable);
	
	List<Message> findByGroupIdOrderByCreatedAtAsc(Long groupId);

	Page<Message> findByGroupId(Long groupId, Pageable pageable);

	//Page<Message> findByGroupId(Long groupId, Pageable pageable);
}


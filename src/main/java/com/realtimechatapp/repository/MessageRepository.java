package com.realtimechatapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.realtimechatapp.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	Page<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId, Pageable pageable);

	List<Message> findByGroupIdOrderByCreatedAtAsc(Long groupId);

	Page<Message> findByGroupId(Long groupId, Pageable pageable);
	
	  Optional<Message> findById(Long id);

	// Page<Message> findByGroupId(Long groupId, Pageable pageable);

	@Query("""
			    SELECT m
			    FROM Message m
			    WHERE
			    (m.senderId = :senderId AND m.receiverId = :receiverId)
			    OR
			    (m.senderId = :receiverId AND m.receiverId = :senderId)
			    ORDER BY m.createdAt ASC
			""")
	Page<Message> getChatHistory(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId,
			Pageable pageable);

}

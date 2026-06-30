package com.realtimechatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realtimechatapp.entity.ChatGroup;

@Repository
public interface GroupRepository extends JpaRepository<ChatGroup, Long> {

}

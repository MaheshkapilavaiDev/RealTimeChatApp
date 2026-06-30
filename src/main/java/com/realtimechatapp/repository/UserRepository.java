package com.realtimechatapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realtimechatapp.entity.User;
import com.realtimechatapp.enums.UserStatus;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
	List<User> findByStatus(UserStatus status);

}

package com.realtimechatapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.realtimechatapp.dto.LoginRequest;
import com.realtimechatapp.dto.RegisterRequest;
import com.realtimechatapp.entity.User;
import com.realtimechatapp.enums.UserStatus;
import com.realtimechatapp.exception.InvalidCredentialsException;
import com.realtimechatapp.exception.ResourceNotFoundException;
import com.realtimechatapp.exception.UserAlreadyExistsException;
import com.realtimechatapp.repository.MessageRepository;
import com.realtimechatapp.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String registerUser(RegisterRequest request) {

		Optional<User> existingUser = userRepository.findByUsername(request.getUsername());

		if (existingUser.isPresent()) {

			throw new UserAlreadyExistsException("Username already exists");

		}

		User user = new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setStatus(UserStatus.OFFLINE);

		userRepository.save(user);

		return "User Registered Successfully";

	}

	public String loginUser(LoginRequest request) {

		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new InvalidCredentialsException("Invalid Password");
		}

		user.setStatus(UserStatus.ONLINE);
		userRepository.save(user);

		return jwtService.generateToken(user.getUsername());

	}
	
	public void logout(String username) {

	    User user = userRepository.findByUsername(username)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	    user.setStatus(UserStatus.OFFLINE);

	    userRepository.save(user);
	}
	public List<User> getOnlineUsers() {

        return userRepository.findByStatus(UserStatus.ONLINE);

    }
	}


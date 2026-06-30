package com.realtimechatapp.controller;

import org.springframework.security.core.Authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.realtimechatapp.dto.LoginRequest;
import com.realtimechatapp.dto.RegisterRequest;
import com.realtimechatapp.entity.User;
import com.realtimechatapp.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest request) {

		return authService.registerUser(request);
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {

		return authService.loginUser(request);
	}

	@PostMapping("/logout")
	public String logout(Authentication authentication) {

		authService.logout(authentication.getName());

		return "Logged Out Successfully";
	}
	
	@GetMapping("/online")
    public List<User> getOnlineUsers() {

        return authService.getOnlineUsers();

    }
}

package com.hans.service;

import com.hans.payload.LoginDto;
import com.hans.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}

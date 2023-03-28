package com.verter.service;

import com.verter.auth.AuthResponse;
import com.verter.auth.LoginRequest;
import com.verter.auth.RegisterRequest;

public interface AuthService {
  void register(RegisterRequest request);

  AuthResponse login(LoginRequest request);
}

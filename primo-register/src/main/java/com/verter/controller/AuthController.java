package com.verter.controller;

import com.verter.auth.AuthResponse;
import com.verter.auth.LoginRequest;
import com.verter.auth.RegisterRequest;
import com.verter.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/register")
  public void register(@RequestBody @Valid RegisterRequest request) {
    authService.register(request);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PostMapping("/login")
  public AuthResponse login(@RequestBody @Valid LoginRequest request) {
    return authService.login(request);
  }

}

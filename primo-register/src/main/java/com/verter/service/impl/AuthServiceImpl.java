package com.verter.service.impl;

import com.verter.auth.AuthResponse;
import com.verter.auth.LoginRequest;
import com.verter.auth.RegisterRequest;
import com.verter.config.jwt.JwtService;
import com.verter.exception.EmailAlreadyRegisteredException;
import com.verter.model.Role;
import com.verter.model.User;
import com.verter.model.constant.RoleConst;
import com.verter.repository.UserRepository;
import com.verter.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public void register(RegisterRequest request) {
    userRepository.findByEmail(request.email()).ifPresent(user -> {
      throw new EmailAlreadyRegisteredException();
    });

    User user = new User(request.firstName(),
                         request.lastName(),
                         request.email(),
                         request.phoneNumber(),
                         passwordEncoder.encode(request.password()),
                         List.of(new Role(RoleConst.USER)));

    userRepository.save(user);
  }

  @Override
  public AuthResponse login(LoginRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.email(),
        request.password()
      )
    );

    User user = userRepository.findByEmail(request.email())
                              .orElseThrow();

    String token = jwtService.generateToken(user.getEmail());

    return new AuthResponse(token);
  }
}

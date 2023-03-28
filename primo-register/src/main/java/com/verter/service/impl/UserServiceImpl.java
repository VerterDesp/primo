package com.verter.service.impl;

import com.verter.repository.UserRepository;
import com.verter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    return userRepository.findByEmail(login)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}

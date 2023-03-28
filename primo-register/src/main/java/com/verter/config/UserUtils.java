package com.verter.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUtils {

  public static Authentication getAuth() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public static void setAuth(Authentication auth) {
    SecurityContextHolder.getContext().setAuthentication(auth);
  }



}

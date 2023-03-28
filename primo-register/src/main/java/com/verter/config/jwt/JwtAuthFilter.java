package com.verter.config.jwt;

import com.verter.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.verter.config.UserUtils.getAuth;
import static com.verter.config.UserUtils.setAuth;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserService userService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String tokenHead = "Bearer ";
    final String jwtToken;
    final String userEmail;

    if(authHeader == null || !authHeader.startsWith(tokenHead)) {
      filterChain.doFilter(request, response);
      return;
    }

    jwtToken = authHeader.substring(tokenHead.length());
    userEmail = jwtService.extractUsername(jwtToken);

    if(userEmail != null && getAuth() == null) {
      UserDetails userDetails = userService.loadUserByUsername(userEmail);

      if (jwtService.isTokenValid(jwtToken, userDetails.getUsername())) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities()
        );

        authToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request)
        );

        setAuth(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}

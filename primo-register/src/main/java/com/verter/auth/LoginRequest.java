package com.verter.auth;

import com.verter.config.ConstraintsConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record LoginRequest(@Size(min = ConstraintsConstants.USER_EMAIL_MIN_SIZE, max = ConstraintsConstants.USER_EMAIL_MAX_SIZE)
                           @Email
                           String email,
                           @Size(min = ConstraintsConstants.USER_PASSWORD_MIN_SIZE, max = ConstraintsConstants.USER_PASSWORD_MAX_SIZE)
                           String password) {
}

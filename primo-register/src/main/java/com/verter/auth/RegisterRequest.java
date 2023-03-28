package com.verter.auth;

import com.verter.config.ConstraintsConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


public record RegisterRequest(@Size(min = ConstraintsConstants.USER_NAME_MIN_SIZE, max = ConstraintsConstants.USER_NAME_MAX_SIZE)
                              String firstName,
                              @Size(min = ConstraintsConstants.USER_NAME_MIN_SIZE, max = ConstraintsConstants.USER_NAME_MAX_SIZE)
                              String lastName,
                              @Size(min = ConstraintsConstants.USER_EMAIL_MIN_SIZE, max = ConstraintsConstants.USER_EMAIL_MAX_SIZE)
                              @Email
                              String email,
                              String phoneNumber,
                              @Size(min = ConstraintsConstants.USER_PASSWORD_MIN_SIZE, max = ConstraintsConstants.USER_PASSWORD_MAX_SIZE)
                              String password) {
}

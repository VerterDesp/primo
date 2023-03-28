package com.verter.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstraintsConstants {

  public static final int USER_NAME_MIN_SIZE = 2;
  public static final int USER_NAME_MAX_SIZE = 40;
  public static final int USER_EMAIL_MIN_SIZE = 5;
  public static final int USER_EMAIL_MAX_SIZE = 50;
  public static final int USER_PASSWORD_MIN_SIZE = 8;
  public static final int USER_PASSWORD_MAX_SIZE = 100;
}

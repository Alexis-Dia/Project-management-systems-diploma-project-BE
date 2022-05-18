package com.projectManagement.util;

import java.util.Objects;

public final class StringUtils {

  public static final String SPACE = " ";
  public static final String EMPTY = "";
  public static final String PERCENT = "%";
  public static final String DOT = ".";
  public static final String COMMA = ",";
  public static final String COMMA_SPACE = COMMA + SPACE;

  public static boolean nullOrEmpty(String str) {

    return Objects.isNull(str) || str.isEmpty();
  }

  public static boolean nonNullNonEmpty(String str) {

    return Objects.nonNull(str) && !str.isEmpty();
  }
}

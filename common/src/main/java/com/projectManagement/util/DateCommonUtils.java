package com.projectManagement.util;

import java.util.Date;

public class DateCommonUtils {

  public static Date addMilliseconds(Date date, long ms) {

    return org.apache.commons.lang3.time.DateUtils.addMilliseconds(date, (int) ms);
  }

  public static Date addSeconds(Date date, int seconds) {

    return org.apache.commons.lang3.time.DateUtils.addSeconds(date, seconds);
  }

  public static Date now() {

    return new Date();
  }

  public static Date zeroTime() {

    return new Date(0);
  }

}

package com.common.mapper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationConstants {

  /**
   * Inner utility class for dto validation.
   */
  @UtilityClass
  public class DataValidation {

    public static final int MIN_SIZE_OF_NAME = 5;
    public static final int MAX_SIZE_OF_NAME = 255;
    public static final int MIN_SIZE_OF_SURNAME = 5;
    public static final int MAX_SIZE_OF_SURNAME = 255;
    public static final int MIN_SIZE_OF_EMAIL = 5;
    public static final int MAX_SIZE_OF_EMAIL = 100;
    public static final int MIN_SIZE_OF_DATE_OF_BIRTH = 10;
    public static final int MAX_SIZE_OF_DATE_OF_BIRTH = 10;
    public static final int MIN_SIZE_OF_ADDRESS = 1;
    public static final int MAX_SIZE_OF_ADDRESS = 255;
  }

}

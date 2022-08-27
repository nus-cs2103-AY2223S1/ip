package cheese.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains utility methods to parse date time related objects.
 */
public class DateTimeUtils {
  /** Formatter used to parse input. */
  private static final DateTimeFormatter INPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");

  /** Formatter used to parse output. */
  private static final DateTimeFormatter OUTPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

  /**
   * Parses string to date time using input formatter.
   * 
   * @param dateTime String to parse.
   * @return Parsed date time.
   */
  public static LocalDateTime parseString(String dateTime) {
    return LocalDateTime.parse(dateTime, INPUT_DATETIME_FORMATTER);
  }

  /**
   * Parses date time to string using output formatter.
   * 
   * @param dateTime Date time to parse.
   * @return Parsed string representing date time.
   */
  public static String parseLocalDateTimeToOutput(LocalDateTime dateTime) {
    return OUTPUT_DATETIME_FORMATTER.format(dateTime);
  }

  /**
   * Parses date time to string using input formatter.
   * 
   * @param dateTime Date time to parse.
   * @return Parsed string representing date time.
   */
  public static String parseLocalDateTimeToInput(LocalDateTime dateTime) {
    return INPUT_DATETIME_FORMATTER.format(dateTime);
  }
}

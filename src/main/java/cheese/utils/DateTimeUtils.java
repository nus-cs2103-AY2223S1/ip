package cheese.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
  private static final DateTimeFormatter INPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
  private static final DateTimeFormatter OUTPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

  public static LocalDateTime parseString(String dateTime) {
    return LocalDateTime.parse(dateTime, INPUT_DATETIME_FORMATTER);
  }

  public static String parseLocalDateTimeToOutput(LocalDateTime dateTime) {
    return OUTPUT_DATETIME_FORMATTER.format(dateTime);
  }

  public static String parseLocalDateTimeToInput(LocalDateTime dateTime) {
    return INPUT_DATETIME_FORMATTER.format(dateTime);
  }
}

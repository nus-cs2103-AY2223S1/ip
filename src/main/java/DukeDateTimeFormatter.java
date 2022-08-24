import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates date-time formatting for Duke tasks.
 *
 * @author Kartikeya
 */
public class DukeDateTimeFormatter {
  /**
   * Formats date-time inputs to the required `MMM d, yyyy | h:mma` format
   * @param input Input string from event
   * @return Formatted string
   */
  public static String format(String input) {
    String[] dateAndTime = input.split(" ");

    // Parse input date and time separately
    LocalDate date = LocalDate.parse(dateAndTime[0]);
    LocalTime time = LocalTime.parse(dateAndTime[1], DateTimeFormatter.ofPattern("HHmm"));

    // Format parsed inputs into `MMM d, yyyy | h:mma` format
    String returnDate = date.format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
    String returnTime = time.format(DateTimeFormatter.ofPattern("h:mma"));
    return returnDate + " | " + returnTime;
  }
}

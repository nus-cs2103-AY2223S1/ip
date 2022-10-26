package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Encapsulates date-time formatting for Artemis tasks.
 *
 * @author Kartikeya
 */
public class DukeDateTimeFormatter {
    /**
     * Formats date-time inputs to the required `MMM d, yyyy | h:mma` format
     *
     * @param input input string from event
     * @return formatted string
     */
    public static String format(String input) throws DukeException {
        try {
            String[] dateAndTime = input.split(" ");

            // Parse input date and time separately
            LocalDate date = LocalDate.parse(dateAndTime[0]);
            LocalTime time = LocalTime.parse(dateAndTime[1],
                    DateTimeFormatter.ofPattern("HHmm"));

            // Format parsed inputs into `MMM d, yyyy | h:mma` format
            String returnDate = date.format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
            String returnTime = time.format(DateTimeFormatter.ofPattern("h:mma"));
            return returnDate + " | " + returnTime;
        } catch (DateTimeParseException | IndexOutOfBoundsException f) {
            throw new DukeException(
                    "Please format your date/time in this 24-hour format: \"yyyy-mm-dd hhmm\""
            );
        }
    }
}

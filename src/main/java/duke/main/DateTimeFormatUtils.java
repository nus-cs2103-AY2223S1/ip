package duke.main;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class encapsulating operations on LocalDateTime for task logging.
 */
public class DateTimeFormatUtils {
    /* Formatter objects for valid input and output types */
    private final static DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final static DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    /**
     * Returns LocalDateTime object given String representing a date.
     * Throws DukeException if input string does not match valid format.
     *
     * @param date String containing date information.
     * @return LocalDateTime object retrieved from parsing input string.
     * @throws DukeException If input string is of invalid formatting.
     */
    public static LocalDateTime parseDate(String date) throws DukeException {
        try {
            LocalDateTime inputDate = LocalDateTime.parse(date, INPUT_FORMAT);
            return inputDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("\tDate Format Police here!!" +
                    "\n\tYour deadline has to be in YYYY-MM-DD HHmm format!!");
        }
    }

    /**
     * Returns array of LocalDateTime objects given an input string corresponding to
     * a given duration, consisting of two dates separated by a 'to' regex.
     * Throw DukeException if input string does not match valid format.
     *
     * @param date String containing date information.
     * @return Array of LocalDateTime objects retrieved from parsing input string.
     * @throws DukeException If input string is of invalid formatting.
     */
    public static LocalDateTime[] parseDuration(String date) throws DukeException {
        try {
            String[] input = date.split(" to ");
            LocalDateTime startDate = LocalDateTime.parse(input[0], INPUT_FORMAT);
            LocalDateTime endDate = LocalDateTime.parse(input[1], INPUT_FORMAT);
            return new LocalDateTime[]{startDate, endDate};
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\tDate Format Police here!!" +
                    "\n\tYour deadline has to be in YYYY-MM-DD HHmm to YYYY-MM-DD HHmm format!!");
        }
    }

    /**
     * Return string representation of given LocalDateTime object in valid output format.
     *
     * @param date LocalDateTime object.
     * @return String representation of given LocalDatetime object.
     */
    public static String printDate(LocalDateTime date) {
        String output = date.format(OUTPUT_FORMAT);
        return output;
    }

    /**
     * Returns string representation of given LocalDateTime object in valid input format.
     *
     * @param date LocalDateTime object.
     * @return String representation of given LocalDatetime object.
     */
    public static String convertToInputFormat(LocalDateTime date) {
        String output = date.format(INPUT_FORMAT);
        return output;
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Handles the parsing of dates for Deadlines/Events
     * @param input String input of date
     * @return Date object used to construct Deadline/Event
     * @throws DukeException Exception if date format is wrong
     */
    public static Date parseDate(String input) throws DukeException{
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsed = LocalDate.parse(input, dtf);

            return new Date(parsed);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please follow the Date and Time Format: yyyy-MM-dd [2000-01-01]");
        }

    }

    /**
     * Handles the parsing of dates for Deadlines/Events
     * @param input String input of date
     * @return Date object used to construct Deadline/Event
     * @throws DukeException Exception if date format is wrong
     */
    public static Date parseDateSave(String input) throws DukeException{
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate parsed = LocalDate.parse(input, dtf);
            return new Date(parsed);

        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The save file is corrupted.");
        }

    }
}

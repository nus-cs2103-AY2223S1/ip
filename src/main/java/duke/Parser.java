package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import duke.response.DeadlineResponse;
import duke.response.DeleteResponse;
import duke.response.DukeResponse;
import duke.response.EventResponse;
import duke.response.ExitResponse;
import duke.response.FindResponse;
import duke.response.ListResponse;
import duke.response.MarkResponse;
import duke.response.TodoResponse;
import duke.response.UnmarkResponse;

/**
 * Parses user input and converts strings to datetime formats.
 */
public class Parser {
    /**
     * Returns a DukeResponse based on input.
     *
     * @param list The task list.
     * @param input The user input.
     * @return An appropriate DukeResponse.
     * @throws DukeException If unable to read command.
     */
    public static DukeResponse getResponse(DukeList list, String input) throws DukeException {
        DukeCommand command = getCommand(input);
        String data = getData(input);

        switch (command) {
        case EXIT:
            // Exit duke.Duke
            return new ExitResponse();
        case LIST:
            // Print list
            return new ListResponse(list);
        case FIND:
            return new FindResponse(list, data);
        case MARK:
            // Mark duke.task as done
            return new MarkResponse(list, data);
        case UNMARK:
            // Mark duke.task as undone
            return new UnmarkResponse(list, data);
        case DELETE:
            return new DeleteResponse(list, data);
        case TODO:
            // Add duke.task as to do
            return new TodoResponse(list, data);
        case DEADLINE:
            // Add duke.task as deadline
            return new DeadlineResponse(list, data);
        case EVENT:
            // Add duke.task as event
            return new EventResponse(list, data);
        default:
            // Unknown command
            throw new DukeException("I'm sorry, but I don't know what that means :(");
        }
    }

    private static DukeCommand getCommand(String input) {
        return DukeCommand.read(input.split(" ", 2)[0]);
    }

    private static String getData(String input) {
        if (input.contains(" ")) {
            return input.split(" ", 2)[1].trim();
        }
        return "";
    }

    /**
     * Converts a String with date and time from user input to LocalDateTime.
     *
     * @param dateTimeStr The String with a date and time.
     * @return A LocalDateTime object.
     * @throws DukeException If the String contains an invalid date time format.
     */
    public static LocalDateTime strToDate(String dateTimeStr) throws DukeException {
        try {
            DateTimeFormatterBuilder formatBuilder = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("[d MMM yyyy h[:mm][ ]a]") // 23 Aug 2022 6[:30][ ]pm
                    .appendPattern("[d MMM h[:mm][ ]a]")      // 23 Aug 6[:30][ ]pm
                    .appendPattern("[d/M/yyyy HHmm]")         // 23/8/2022 1830
                    .appendPattern("[d/M HHmm]")              // 23/8 1830
                    .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDateTime.now().getYear())
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0);
            return LocalDateTime.parse(dateTimeStr, formatBuilder.toFormatter());
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to read date and time.");
        }
    }

    /**
     * Converts a String with date and time from data file to LocalDateTime.
     *
     * @param dateTimeStr The String with a date and time.
     * @return A LocalDateTime object.
     */
    public static LocalDateTime strToDateFromStorage(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr);
    }
}

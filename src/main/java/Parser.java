import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class Parser {
    public static DukeResponse getResponse(DukeList list, String input) throws DukeException {
        DukeCommand command = getCommand(input);
        String data = getData(input);

        switch (command) {
        case EXIT:
            // Exit Duke
            return new ExitResponse();
        case LIST:
            // Print list
            return new ListResponse(list);
        case MARK:
            // Mark task as done
            return new MarkResponse(list, data);
        case UNMARK:
            // Mark task as undone
            return new UnmarkResponse(list, data);
        case DELETE:
            return new DeleteResponse(list, data);
        case TODO:
            // Add task as to do
            return new TodoResponse(list, data);
        case DEADLINE:
            // Add task as deadline
            return new DeadlineResponse(list, data);
        case EVENT:
            // Add task as event
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

    public static LocalDateTime strToDate(String dateTimeStr) throws DukeException {
        try {
            DateTimeFormatterBuilder formatBuilder = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("[d MMM yyyy h[:mm][ ]a]")   // 23 Aug 2022 6[:30][ ]pm
                    .appendPattern("[d MMM h[:mm][ ]a]")        // 23 Aug 6[:30][ ]pm
                    .appendPattern("[d/M/yyyy HHmm]")           // 23/8/2022 1830
                    .appendPattern("[d/M HHmm]")                // 23/8 1830
                    .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDateTime.now().getYear())
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0);
            return LocalDateTime.parse(dateTimeStr, formatBuilder.toFormatter());
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to read date and time.");
        }
    }

    public static LocalDateTime strToDateFromStorage(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr);
    }
}

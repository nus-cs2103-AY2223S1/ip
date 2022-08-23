import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public abstract class DukeResponse {
    private static final String DIVIDER = "____________________________________________________________";

    public static void intro() {
        String text = "Hello! I'm Duke!\n" + "What can I do for you?";
        System.out.println(DIVIDER + "\n" + text + "\n" + DIVIDER);
    }

    public static void outro() {
        String text = "Bye. Hope to see you again soon!";
        System.out.println(DIVIDER + "\n" + text + "\n" + DIVIDER);
    }

    public void message(String text) {
        System.out.println(DIVIDER + "\n" + text + "\n" + DIVIDER);
    }

    public LocalDateTime parseStrToDate(String dateTimeStr) throws DukeException {
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

    public abstract void run() throws DukeException;
}
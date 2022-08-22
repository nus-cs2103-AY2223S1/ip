import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final DateTimeFormatter dashFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter slashFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    private static final String eventBreak = "/at ";
    private static final String deadlineBreak = "/by ";

    public static Pair<String, LocalDateTime> parseEvent(String input) throws DaveException {
        if (input.equals("")) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! The description of an event cannot be empty!");
        }
        String[] args = input.split("/at ");
        if (args.length > 2) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! Too many timings given, Dave's brain is fried!");
        } else if (args.length < 2) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! Please provide a timing for the event!");
        }

        String task = args[0];
        String dateStr = args[1];
        LocalDateTime dateTime;

        try {
            if (dateStr.contains("/")) {
                dateTime = LocalDateTime.parse(args[1], slashFormat);
            } else {
                dateTime = LocalDateTime.parse(args[1], dashFormat);
            }
        } catch (DateTimeParseException e) {
            throw new DaveException("Please input a valid date!");
        }

        return new Pair<>(task, dateTime);
    }

    public static Pair<String, LocalDateTime> parseDeadline(String input) throws DaveException {
        if (input.equals("")) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! The description of an event cannot be empty!");
        }
        String[] args = input.split("/by ");
        if (args.length > 2) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! Too many timings given, Dave's brain is fried!");
        } else if (args.length < 2) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! Please provide a timing for the event!");
        }

        String task = args[0];
        String dateStr = args[1].trim();
        LocalDateTime dateTime;

        try {
            if (dateStr.contains("/")) {
                dateTime = LocalDateTime.parse(dateStr, slashFormat);
            } else {
                System.out.println("aaaaaa");
                dateTime = LocalDateTime.parse(dateStr, dashFormat);
            }
        } catch (DateTimeParseException e) {
            throw new DaveException("Please input a valid date!");
        }

        return new Pair<>(task, dateTime);
    }
}

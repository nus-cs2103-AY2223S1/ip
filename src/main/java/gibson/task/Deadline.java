package gibson.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline represents a deadline with a task string and
 * a date and time as a deadline.
 * It is a task with a deadline.
 */
public class Deadline extends Task {

    private static final String[] FORMATS = new String[] {
        "y-M-d H:m", "d MMM yyyy H:m"
    };
    private LocalDateTime date;

    /**
     * Constructs a Deadline object that is represented by a task and a date.
     * @param taskString the task
     * @param date the deadline of given task
     */
    public Deadline(String taskString, String date) {
        super(taskString);
        if (date.isBlank()) {
            throw new IllegalArgumentException("Time of deadline cannot be empty.");
        } else {
            assert FORMATS.length > 0 : "No DateTime format provided.";
            for (int i = 0; i < FORMATS.length; i++) {
                try {
                    this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(FORMATS[i]));
                    break;
                } catch (DateTimeParseException e) {
                    if (i == FORMATS.length - 1) {
                        throw new IllegalArgumentException(
                                "Date format is invalid. Try it in y-M-d H:m. For example, 2020-1-12 23:59.");
                    }
                }
            }
        }
    }

    @Override
    public char getChar() {
        return 'D';
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy H:m")) + ")";
    }
}

package gibson.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private static final String[] formats = new String[] {
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
            for (int i = 0; i < formats.length; i++) {
                try {
                    this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(formats[i]));
                    break;
                } catch (DateTimeParseException e) {
                    if (i == formats.length - 1) {
                        throw new IllegalArgumentException("Date format is invalid. Try it in y-M-d H:m. For example, 2020-1-12 23:59.");
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
        return super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy H:m")) + ")";
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime date;

    public Deadline(String taskString, String date) {
        super(taskString);
        if (date.isBlank()) {
            throw new IllegalArgumentException("Time of deadline cannot be empty.");
        } else {
            try {
                this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("y-M-d H:m"));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Date format is invalid. Try it in y-M-d H:m. For example, 2020-1-12 23:59.");
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

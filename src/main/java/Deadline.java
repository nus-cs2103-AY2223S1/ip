import exceptions.TaskDescriptionEmpty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final String typeIcon = "D";
    private String time;

    public Deadline(String description, String time) throws TaskDescriptionEmpty {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        String[] words = this.time.split(" ");
        StringBuilder sbFormattedTime = new StringBuilder();
        if (words.length >= 1) {
            try {
                LocalDate date = LocalDate.parse(words[0]);
                sbFormattedTime.append(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            } catch (DateTimeParseException ex) {
                sbFormattedTime.append(this.time);
            }
        }
        return String.format("[%s]%s (by: %s)", typeIcon, super.toString(), sbFormattedTime);
    }
}

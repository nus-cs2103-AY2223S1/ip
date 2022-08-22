package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate date;
    private DateTimeFormatter formatter;

    Deadline(String description, boolean isDone, String dateString) {
        super(description, isDone);
        try {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public String SaveString() {
        return String.format("D | %s | %s | %s\n", super.isDone ? "1" : "0",
                super.description, date.format(formatter));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), date.format(formatter));
    }
}

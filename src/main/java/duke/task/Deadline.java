package duke.task;

import duke.DateConverter;
import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.date = LocalDate.parse(by);
    }

    @Override
    public String storedString() {
        return "D | " + super.storedString() + " | " + date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + DateConverter.convertToString(date) + ")";
    }
}

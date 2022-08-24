package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.parser.Parser;

public class Deadline extends Task {
    private static final String TASK_SYMBOL = "D";
    private static final String DATE_OUTPUT_FORMAT = "d MMM yyyy";
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Deadline.TASK_SYMBOL, super.toString(), this.getFormattedDate());
    }

    @Override
    public String getType() {
        return Deadline.TASK_SYMBOL;
    }

    @Override
    public String getDate() {
        return this.by;
    }

    private String getFormattedDate() {
        LocalDate date = Parser.parseDate(this.by);
        return date.format(DateTimeFormatter.ofPattern(DATE_OUTPUT_FORMAT));
    }
}

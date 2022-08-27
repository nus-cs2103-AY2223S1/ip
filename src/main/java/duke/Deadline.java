package duke;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs an instance of Deadline
     *
     * @param description Description String
     * @param by Date String
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateTimeConverter.formatDate(by);
    }

    /** @inheritdoc */
    @Override
    public String changeFormat() {
        String done = isDone ? "1" : "0";
        return "D | " + done + " | " + this.description + " | " + this.by;
    }

    /** @inheritdoc */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + DateTimeConverter.formatString(by) + ")";
    }
}

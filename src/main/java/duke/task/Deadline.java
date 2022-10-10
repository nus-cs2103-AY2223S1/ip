package duke.task;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.ui.Ui;

/**
 * Class for a singular Deadline.
 */
public class Deadline extends Task {
    private String deadline;
    private LocalDate date;

    /**
     * Constructor for Deadline.
     * @param description - description of the deadline
     * @param deadline - deadline of the deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructor for Deadline.
     * @param description - description of the deadline
     * @param date - deadline of the deadline
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return isNull(this.deadline) ? this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : deadline;
    }

    @Override
    public String getSavedString() {
        return String.format("D | %s | %s | %s\n", this.getIsDone(), this.getDescription(), getDate());
    }

    @Override
    public String toString() {
        return String.format(
                "[D] %s %s (by: %s)", Ui.checkbox(this.getIsDone()), this.getDescription(), getDate()
        );
    }
}

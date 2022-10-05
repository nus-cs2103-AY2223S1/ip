package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {
    private LocalDateTime deadline;
    private static DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static DateTimeFormatter DATE_TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Constructor that creates Deadline object with specified description, deadline, and isDone status.
     *
     * @param description Description of Deadline.
     * @param deadline    Timing of the Deadline.
     * @param isDone      isDone status of the Deadline.
     * @throws DateTimeParseException Exception thrown when format of input date is invalid.
     */
    public Deadlines(String description, LocalDateTime deadline, boolean isDone) throws DateTimeParseException {
        super(isDone);
        this.description = description;
        this.deadline = deadline;
    }


    @Override
    public String processData() {
        String str;
        if (this.getIsDone()) {
            str = String.format("D|true|%s|%s|", this.getDescription(), this.deadline.format(DATE_TIME_INPUT_FORMAT));
        } else {
            str = String.format("D|false|%s|%s|", this.getDescription(), this.deadline.format(DATE_TIME_INPUT_FORMAT));
        }
        return str;
    }

    @Override
    public String toString() {
        String str;
        if (this.getIsDone()) {
            str = String.format("[D] %s [X] (by %s)", this.getDescription(),
                    this.deadline.format(DATE_TIME_OUTPUT_FORMAT));
        } else {
            str = String.format("[D] %s [ ] (by %s)", this.getDescription(),
                    this.deadline.format(DATE_TIME_OUTPUT_FORMAT));
        }
        return str;
    }
}

package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DoAfter extends Task {
    private LocalDateTime startTime;
    private static DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static DateTimeFormatter DATE_TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Constructor that creates DoAfter object with specified description, start time, and isDone status.
     *
     * @param description Description of task.
     * @param startTime    Timing to start task after.
     * @param isDone      isDone status of the task.
     * @throws DateTimeParseException Exception thrown when format of input date is invalid.
     */
    public DoAfter(String description, LocalDateTime startTime, boolean isDone) throws DateTimeParseException {
        super(isDone);
        this.description = description;
        this.startTime = startTime;
    }

    @Override
    public String processData() {
        String str;
        if (this.getIsDone()) {
            str = String.format("A|true|%s|%s|", this.getDescription(), this.startTime.format(DATE_TIME_INPUT_FORMAT));
        } else {
            str = String.format("A|false|%s|%s|", this.getDescription(), this.startTime.format(DATE_TIME_INPUT_FORMAT));
        }
        return str;
    }

    @Override
    public String toString() {
        String str;
        if (this.getIsDone()) {
            str = String.format("[A] %s [X] (after %s)", this.getDescription(),
                    this.startTime.format(DATE_TIME_OUTPUT_FORMAT));
        } else {
            str = String.format("[A] %s [ ] (after %s)", this.getDescription(),
                    this.startTime.format(DATE_TIME_OUTPUT_FORMAT));
        }
        return str;
    }
}

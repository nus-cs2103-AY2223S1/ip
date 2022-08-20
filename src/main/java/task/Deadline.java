package task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import exception.DeadlineException;

public class Deadline extends Task {
    private String deadlineString;
    private LocalDate deadline;

    public Deadline(String description, String deadlineString) throws DeadlineException {
        super(description);
        try {
            this.deadlineString = deadlineString;
            this.deadline = LocalDate.parse(deadlineString);
        } catch (DateTimeParseException error) {
            throw new DeadlineException("The deadline given is not a valid date. " +
                    "Try to represent the deadline in yyyy-mm-dd format.");
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toStorageRepresentation() {
        return "D|" + super.toStorageRepresentation() + "|" + this.deadlineString;
    }
}

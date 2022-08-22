package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Standard constructor for a deadline
     * @param description The description of the task
     * @param deadline The deadline of the task
     */
    public Deadline(String description, String deadline) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    /**
     * Overloaded constructor that allows for creation of pre-completed deadline tasks
     * @param description The description of the task
     * @param isDone Marks whether the task has been completed before
     * @param deadline The deadline of the task
     */
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    @Override
    public String getSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "DEADLINE,," + super.getSaveString() + this.deadline.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formatted = deadline.format(formatter);
        return String.format("[D]%s (by: %s)", super.toString(), formatted);
    }
}

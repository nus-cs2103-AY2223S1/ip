package duke.task;

import java.time.LocalDate;


/**
 * Represents a Deadline task with a deadline description and end date
 * Inherits from Task class
 */
public class Deadline extends Task {
    private LocalDate end;

    public Deadline(String description, LocalDate end) {
        super(description);
        this.end = end;
    }

    public Deadline(String description, Boolean isDone, LocalDate end) {
        super(description, isDone);
        this.end = end;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[D][X]" : "[D][ ]"); // mark done task with X
    }

    @Override
    public String getDescription() {
        return String.format("%s (by %s)", this.description, this.end);
    }

    public String toString() {
        String status = isDone ? "Done  " : "UnDone";
        return String.format("Deadline  | %s | %s | %s", status, super.getDescription(), this.end);
    }

    @Override
    public String taskType() {
        return "deadline";
    }

    public LocalDate getEnd() {
        return this.end;
    }
}

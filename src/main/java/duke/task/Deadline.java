package duke.task;

import java.time.LocalDate;
import java.util.List;


/**
 * Represents a Deadline task with a deadline description and end date. This class inherits Task class.
 */
public class Deadline extends Task {
    private LocalDate end;

    /**
     * This method is a Deadline Task constructor.
     * A deadline Task contains a task description, a task status, tags, deadline end date.
     * @param description a task description string.
     * @param end a LocalDate object of deadline end date.
     */
    public Deadline(String description, LocalDate end) {
        super(description);
        this.end = end;
    }

    /**
     * This method is an overloaded Deadline Task constructor.
     * @param description a task description string.
     * @param isDone deadline task status (false: not done. true: done).
     * @param tags a list of tags.
     * @param end a LocalDate object of deadline end date.
     */
    public Deadline(String description, Boolean isDone, LocalDate end, List<String> tags) {
        super(description, isDone, tags);
        this.end = end;
    }

    /**
     * Returns Deadline task status icon. This method overrides the method from its super class Task.
     * In the status icon, [D] represents task type Deadline, [X] represents task status as done,
     * [ ] represents task status as not done.
     * @return String representation of Deadline task status icon.
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "[D][X]" : "[D][ ]"); // mark done task with X
    }

    /**
     * Returns a string of all deadline information message, including deadline description and end date.
     * This method overrides the method from its super class Task.
     * @return a string of all deadline information message.
     */
    @Override
    public String getDescription() {
        return String.format("%s (by %s)", this.description, this.end);
    }

    /**
     * Returns a string format of Deadline task. The format is | Deadline | status | description | time | tags
     * This method is used to convert a Deadline task to string and store it in storage.
     * @return string format of Deadline task.
     */
    public String toString() {
        String status = isDone ? "Done  " : "UnDone";
        return String.format("Deadline  | %s | %s | %s | %s", status, super.getDescription(), this.end, super.printTags());
    }

    /**
     * Returns the task type of Deadline task. This method overrides the method from its super class Task.
     * @return string "deadline".
     */
    @Override
    public String taskType() {
        return "deadline";
    }

    /**
     * Returns the end date of Deadline task.
     * @return LocalDate object end.
     */
    public LocalDate getEnd() {
        return this.end;
    }
}

package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline tasks in the task list.
 */
public class Deadline extends Task {

    private final LocalDateTime time;

    /**
     * Constructs an unmarked {@link Deadline} object.
     *
     * @param name Name of the task.
     * @param time Time of the task's deadline.
     */
    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    /**
     * Constructs a marked or an unmarked {@link Deadline} object.
     *
     * @param name Name of the task.
     * @param time Deadline of the task.
     * @param isDone The marked status of the task.
     */
    public Deadline(String name, LocalDateTime time, boolean isDone) {
        super(name, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.getType(), super.toString(), this.getTime());
    }

    /**
     * Describes the object in a specific format for saving it to the text file.
     *
     * @return String representation of the object.
     */
    @Override
    public String toFileString() {
        return String.format("%s||%s", super.toFileString(), this.getTime());
    }

    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm"));
    }

    public String getType() {
        return "D";
    }
}

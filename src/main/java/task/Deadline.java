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
     * @param time Deadline time of the task.
     */
    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    /**
     * Constructs a marked or an unmarked {@link Deadline} object.
     *
     * @param name Name of the task.
     * @param time Deadline time of the task.
     * @param isDone Marked status of the task.
     */
    public Deadline(String name, LocalDateTime time, boolean isDone) {
        super(name, isDone);
        this.time = time;
    }

    /**
     * Constructs a marked or an unmarked {@link Deadline} object with tag.
     *
     * @param name Name of the task.
     * @param time Deadline time of the task.
     * @param tag Tag name of the task.
     */
    public Deadline(String name, LocalDateTime time, String tag) {
        super(name, tag);
        this.time = time;
    }

    /**
     * Constructs a marked or an unmarked {@link Deadline} object with tag.
     *
     * @param name Name of the task.
     * @param time Deadline time of the task.
     * @param isDone Marked status of the task.
     * @param tag Tag name of the task.
     */
    public Deadline(String name, LocalDateTime time, boolean isDone, String tag) {
        super(name, isDone, tag);
        this.time = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.getType(), super.toString(), this.getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return String.format("%s||%s", super.toFileString(), this.getTime());
    }

    /**
     * {@inheritDoc}
     */
    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm"));
    }

    /**
     * {@inheritDoc}
     */
    public String getType() {
        return "D";
    }
}

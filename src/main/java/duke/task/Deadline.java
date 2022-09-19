package duke.task;

import static duke.common.Messages.DEADLINE_ID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * Contains LocalDateTime information.
 */
public class Deadline extends Task {
    private final LocalDateTime time;

    /**
     * Constructs Deadline object.
     * Allows the program to change isDone status and LocalDateTime of Events.
     *
     * @param detail String of detail extracted from user's raw input.
     * @param isDone True/False of the task's done status.
     * @param time LocalDateTime of the Deadlines object.
     */
    public Deadline(String detail, boolean isDone, LocalDateTime time) {
        super(detail, isDone);
        this.time = time;
    }

    /**
     * Constructs Deadline object.
     * isDone is set to false by default using super class constructor.
     *
     * @param detail String of detail extracted from user's raw input.
     * @param time LocalDateTime of the Events object.
     */
    public Deadline(String detail, LocalDateTime time) {
        super(detail);
        this.time = time;
    }

    @Override
    public Task markDone() {
        return new Deadline(super.getDetail(), true, this.time);
    }

    @Override
    public Task unmarkDone() {
        return new Deadline(super.getDetail(), false, this.time);
    }

    @Override
    public String getId() {
        return DEADLINE_ID;
    }

    @Override
    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return DEADLINE_ID + super.toString()
                + String.format(" (by: %s)", this.time.format(formatter));
    }
}

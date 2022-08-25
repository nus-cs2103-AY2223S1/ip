package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * Contains LocalDateTime information.
 */

public class Deadlines extends Task {
    private static final String ID = "[D]";
    private final LocalDateTime time;

    /**
     * Constructor of Deadlines class.
     * Allows the program to change isDone status and LocalDateTime of Events.
     *
     * @param detail String of detail extracted from user's raw input.
     * @param isDone true/false of the task's done status.
     * @param time LocalDateTime of the Deadlines object.
     */
    public Deadlines(String detail, boolean isDone, LocalDateTime time) {
        super(detail, isDone);
        this.time = time;
    }

    /**
     * Constructor of Deadlines class.
     * isDone is set to false by default using super class constructor.
     *
     * @param detail String of detail extracted from user's raw input.
     * @param time LocalDateTime of the Events object.
     */
    public Deadlines(String detail, LocalDateTime time) {
        super(detail);
        this.time = time;
    }

    @Override
    public Task markDone() {
        return new Deadlines(super.getDetail(), true, this.time);
    }

    @Override
    public Task unmarkDone() {
        return new Deadlines(super.getDetail(), false, this.time);
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return ID + super.toString()
                + String.format("(at: %s)", this.time.format(formatter));
    }
}

package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 * Contains LocalDateTime information.
 */
public class Events extends Task {
    private static final String ID = "[E]";
    private final LocalDateTime time;

    /**
     * Constructs Events object.
     * Allows the program to change isDone status and LocalDateTime of Events.
     *
     * @param detail String of detail extracted from user's raw input.
     * @param isDone true/false of the task's done status.
     * @param time LocalDateTime of the Events object.
     */
    public Events(String detail, boolean isDone, LocalDateTime time) {
        super(detail, isDone);
        this.time = time;
    }

    /**
     * Constructs Events object.
     * isDone is set to false by default using super class constructor.
     *
     * @param detail String of detail extracted from user's raw input.
     * @param time LocalDateTime of the Events object.
     */
    public Events(String detail, LocalDateTime time) {
        super(detail);
        this.time = time;
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
    public Task markDone() {
        return new Events(super.getDetail(), true, this.time);
    }

    @Override
    public Task unmarkDone() {
        return new Events(super.getDetail(), false, this.time);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return ID + super.toString()
                + String.format("(at: %s)", this.time.format(formatter));
    }
}

package rabbit.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of task.
 * It has a content that represents the event and a time
 * when the event happens.
 */
public class Event extends Task {
    private LocalDateTime time;

    /**
     * Constructor of an event. The event is created
     * as not done.
     *
     * @param content the event.
     * @param time the time that the event happens.
     */
    public Event(String content, LocalDateTime time) {
        super(content);
        this.time = time;
    }

    /**
     * Constructor of an event.
     *
     * @param content the event.
     * @param time the time that the event happens.
     * @param isDone whether the event is done.
     */
    public Event(String content, LocalDateTime time, boolean isDone) {
        super(content, isDone);
        this.time = time;
    }

    /**
     * Returns the time of the event.
     *
     * @return the time when the event happens
     */
    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm");
        return this.time.format(formatter);
    }

    /**
     * Sets the time of the task to a new time.
     *
     * @param time the new time.
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timeString = this.time.format(formatter);
        return (this.isDone() ? "[E][X] " : "[E][ ] ") + this.getContent() + " at " + timeString;
    }
}

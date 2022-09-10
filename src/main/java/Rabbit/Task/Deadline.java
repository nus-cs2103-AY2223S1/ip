package rabbit.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of task.
 * It has a content that represents the task and a time
 * when the deadline is.
 */
public class Deadline extends Task{
    private LocalDateTime time;

    /**
     * Constructor of a task that has a deadline. The task is created
     * as not done.
     *
     * @param content the task.
     * @param time the time of the deadline.
     */
    public Deadline(String content, LocalDateTime time) {
        super(content);
        this.time = time;
    }

    /**
     * Constructor of a task that has a deadline. The task is created
     * as not done.
     *
     * @param content the task.
     * @param time the time of the deadline.
     * @param isDone whether the task is done.
     */
    public Deadline(String content, LocalDateTime time, boolean isDone) {
        super(content, isDone);
        this.time = time;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return the deadline.
     */
    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmm");
        return this.time.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timeString = this.time.format(formatter);
        return (this.isDone() ? "[D][X] " : "[D][ ] ") + this.getContent() + " by " + timeString;
    }
}

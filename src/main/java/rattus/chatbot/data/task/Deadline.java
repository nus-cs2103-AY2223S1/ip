package rattus.chatbot.data.task;

import java.time.LocalDateTime;

/**
 * A type of task that has a date for the task to be completed by.
 *
 * @author jq1836
 */
public class Deadline extends TimedTask {
    public Deadline(String description, LocalDateTime dateTime) {
        super(description, dateTime);
    }

    public Deadline(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, dateTime, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + super.getDateString() + ", "
                + super.getTimeString() + ")";
    }

    @Override
    public String encode() {
        return "D" + super.encode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof Deadline;
    }
}

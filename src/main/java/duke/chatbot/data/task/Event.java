package duke.chatbot.data.task;

import java.time.LocalDateTime;

/**
 * A type of task that has a date for the user to attend.
 * @author Jordan Quah Shao Xuan
 */
public class Event extends TimedTask {
    public Event(String description, LocalDateTime dateTime) {
        super(description, dateTime);
    }

    public Event(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, dateTime, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + super.getDateString() + ", "
                + super.getTimeString() + ")";
    }

    @Override
    public String encode() {
        return "E" + super.encode();
    }
}

package duke.chatbot.data.task;

import java.time.LocalDateTime;

public class Deadline extends TimedTask {
    public Deadline(String description, LocalDateTime dateTime) {
        super(description, dateTime);
    }

    public Deadline(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, dateTime, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.getDateString() + " at " + super.getTimeString() + ")";
    }

    @Override
    public String encode() {
        return "D" + super.encode();
    }
}

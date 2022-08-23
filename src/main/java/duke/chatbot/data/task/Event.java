package duke.chatbot.data.task;

import duke.chatbot.data.exception.InvalidInputException;

import java.time.LocalDateTime;

public class Event extends TimedTask {
    public Event(String description, LocalDateTime dateTime) {
        super(description, dateTime);
    }

    public Event(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, dateTime, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.getDateString() + ", " + super.getTimeString() + ")";
    }

    @Override
    public String encode() {
        return "E" + super.encode();
    }
}

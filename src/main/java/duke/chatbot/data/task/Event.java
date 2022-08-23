package duke.chatbot.data.task;

import duke.chatbot.data.exception.InvalidInputException;

public class Event extends TimedTask {
    public Event(String description, String dateTime) throws InvalidInputException {
        super(description, dateTime);
    }

    public Event(String description, String dateTime, boolean isDone) throws InvalidInputException {
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

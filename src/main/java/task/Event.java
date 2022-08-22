package task;

import exception.InvalidInputException;

public class Event extends TimedTask {
    public Event(String description, String dateTime) throws InvalidInputException {
        super(description, dateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + super.getDateString() + ", " + super.getTimeString() + ")";
    }
}

package task;

import exception.InvalidInputException;

public class Deadline extends TimedTask {
    public Deadline(String description, String dateTime) throws InvalidInputException {
        super(description, dateTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + super.getDateString() + " at " + super.getTimeString() + ")";
    }
}

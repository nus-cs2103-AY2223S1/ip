package task;

import exception.InvalidInputException;

public class Deadline extends TimedTask {
    public Deadline(String description, String dateTime) throws InvalidInputException {
        super(description, dateTime);
    }

    public Deadline(String description, String dateTime, boolean isDone) throws InvalidInputException {
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

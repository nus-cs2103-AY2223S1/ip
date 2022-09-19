package task;

import java.time.LocalDate;

import exception.InvalidDateException;
import exception.MissingArgumentException;

public class Event extends Task{

    private final String MESSAGE_ERROR_MISSING_ARGS = "Event command is missing arguments.";
    private final String MESSAGE_ERROR_MISSING_DESCRIPTION = "Description is missing";

    protected LocalDate duration;

    public Event(String description, String duration) throws MissingArgumentException, InvalidDateException {
        super("event", description, duration);
        if (duration.equals("")) {
            throw new MissingArgumentException(MESSAGE_ERROR_MISSING_ARGS);
        }
        if (description.equals("")) {
            throw new MissingArgumentException(MESSAGE_ERROR_MISSING_DESCRIPTION);
        }
        this.duration = super.date;
    }

    public Event(String description, String duration, boolean isDone) throws MissingArgumentException, InvalidDateException {
        super("event", description, duration, isDone);
        if (duration.equals("")) {
            throw new MissingArgumentException(MESSAGE_ERROR_MISSING_ARGS);
        }
        if (description.equals("")) {
            throw new MissingArgumentException(MESSAGE_ERROR_MISSING_DESCRIPTION);
        }
        this.duration = super.date;
    }

    
    /** 
     * returns string representation of event task
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.duration.format(super.outputDateFormatter));
    }
}

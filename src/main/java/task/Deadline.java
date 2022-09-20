package task;

import java.time.LocalDate;

import exception.InvalidDateException;
import exception.MissingArgumentException;

public class Deadline extends Task{

    private final String MESSAGE_ERROR_MISSING_ARGS = "Deadline command is missing arguments.";
    private final String MESSAGE_ERROR_MISSING_DESCRIPTION = "Description is missing";
    
    protected LocalDate date;

    public Deadline(String description, String date) throws MissingArgumentException, InvalidDateException{
        super("deadline", description, date);
        if (date.equals("")) {
            throw new MissingArgumentException(MESSAGE_ERROR_MISSING_ARGS);
        }
        if (description.equals("")) {
            throw new MissingArgumentException(MESSAGE_ERROR_MISSING_DESCRIPTION);
        }
        this.date = super.date;
    }

    public Deadline(String description, String date, boolean isDone) throws MissingArgumentException, InvalidDateException{
        super("deadline", description, date, isDone);
        if (date.equals("")) {
            throw new MissingArgumentException(MESSAGE_ERROR_MISSING_ARGS);
        }
        if (description.equals("")) {
            throw new MissingArgumentException(MESSAGE_ERROR_MISSING_DESCRIPTION);
        }
        this.date = super.date;
    }

    
    /** 
     * returns string representation of deadline task
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date.format(super.outputDateFormatter));
    }
}

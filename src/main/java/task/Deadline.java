package task;
import java.time.LocalDate;

import exception.InvalidDateException;
import exception.MissingArgumentException;

public class Deadline extends Task{

    protected LocalDate date;

    public Deadline(String description, String date) throws MissingArgumentException, InvalidDateException{
        super("deadline", description, date);
        if (date.equals("")) {
            throw new MissingArgumentException("ERROR: deadline command is missing arguments.");
        }
        if (description.equals("")) {
            throw new MissingArgumentException("Description is missing");
        }
        this.date = super.date;
    }

    public Deadline(String description, String date, boolean isDone) throws MissingArgumentException, InvalidDateException{
        super("deadline", description, date, isDone);
        if (date.equals("")) {
            throw new MissingArgumentException("ERROR: deadline command is missing arguments.");
        }
        if (description.equals("")) {
            throw new MissingArgumentException("Description is missing");
        }
        this.date = super.date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date.format(super.outputDateFormatter));
    }
}

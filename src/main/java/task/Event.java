package main.java.task;

import java.time.LocalDate;

import main.java.exception.InvalidDateException;
import main.java.exception.MissingArgumentException;

public class Event extends Task{

    protected LocalDate duration;

    public Event(String description, String duration) throws MissingArgumentException, InvalidDateException {
        super("event", description, duration);
        if (duration.equals("")) {
            throw new MissingArgumentException("ERROR: event command is missing arguments.");
        }
        if (description.equals("")) {
            throw new MissingArgumentException("Description is missing");
        }
        this.duration = super.date;
    }

    public Event(String description, String duration, boolean isDone) throws MissingArgumentException, InvalidDateException {
        super("event", description, duration, isDone);
        if (duration.equals("")) {
            throw new MissingArgumentException("ERROR: event command is missing arguments.");
        }
        if (description.equals("")) {
            throw new MissingArgumentException("Description is missing");
        }
        this.duration = super.date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.duration.format(super.outputDateFormatter));
    }
}

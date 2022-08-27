package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.dukeexception.DateTimeFormatException;
import duke.parser.Parser;

/**
 * Sub-class of task, which has additional event date and time.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Constructs a Event object given description string and datetime string.
     * @param description The name of task.
     * @param at A datetime in format: yyyy-MM-dd hh:mm.
     * @throws DateTimeFormatException
     */
    public Event(String description, String at) throws DateTimeFormatException {
        super(description);
        this.at = Parser.strToDateTime(at);
    }

    /**
     * Adds a new Event task.
     * @param name The name of task.
     * @param at A datetime in format: yyyy-MM-dd hh:mm.
     * @return The generated Event Task.
     * @throws DateTimeFormatException
     */
    public static Event addTask(String name, String at) throws DateTimeFormatException {
        Event newEvent = new Event(name, at);
        System.out.println("       " + newEvent.printSelf());
        return newEvent;
    }

    @Override
    public String recordString() {
        return "E | " + super.recordString() + " | "
                + at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String printSelf() throws DateTimeFormatException {
        return "[E]" + super.printSelf() + " (at: " + Parser.dateTimeToString(this.at) + ")";
    }

}

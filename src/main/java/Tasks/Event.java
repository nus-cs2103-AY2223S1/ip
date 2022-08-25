package Tasks;

import DukeException.DateTimeFormatException;
import Parser.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) throws DateTimeFormatException {
        super(description);
        this.at = Parser.strToDateTime(at);
    }

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

import DukeException.DateTimeFormatException;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) throws DateTimeFormatException {
        super(description);
        this.at = Helper.strToDateTime(at);
    }

    public static Event addTask(String name, String at) throws DateTimeFormatException {
        Event newEvent = new Event(name, at);
        System.out.println("       " + newEvent.printSelf());
        return newEvent;
    }

    @Override
    public String printSelf() throws DateTimeFormatException {
        return "[E]" + super.printSelf() + " (at: " + Helper.dateTimeToString(this.at) + ")";
    }

}

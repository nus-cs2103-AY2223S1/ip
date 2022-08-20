package ren.task;

import ren.RenException;
import ren.TimeStamp;

/**
 * Event Task represents an event that takes place between certain dates/time.
 */
public class Event extends Task {
    protected TimeStamp start;
    protected TimeStamp end;

    /**
     * Constructor for an Event Task.
     *
     * @param description the description of the Event
     * @param dateTime the start and end dates/time of the Event
     */
    public Event(String description, String dateTime) throws RenException {
        super(description);
        String[] duration = dateTime.split(" ~ ", 2);
        this.start = TimeStamp.of(duration[0]);
        this.end = TimeStamp.of(duration[1]);
    }

    private Event(String description, TimeStamp start, TimeStamp end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns an Event constructed with data read from File.
     *
     * @param data Data of an Event.
     * @return Event or null if data is corrupted.
     */
    public static Event readData(String[] data) {
        if (data.length < 5) {
            return null;
        }
        Event newEvent = new Event(data[2], TimeStamp.fromFile(data[3]), TimeStamp.fromFile(data[4]));
        if (data[1].equals("X")) {
            newEvent.setDone(true);
        }
        return newEvent;
    }

    /**
     * Returns the Event information for writing to a File.
     *
     * @return String with Event information.
     */
    @Override
    public String writeData() {
        String symbol = this.isDone ? "X" : " ";
        return "E|" + symbol + "|" + this.description + "|" + this.start + "|" + this.end;
    }

    /**
     * Returns the string representation of this Event.
     *
     * @return String Representation.
     */
    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[E][" + symbol + "] " + this.description + "(at:" + this.start + " -" + this.end + ")\n";
    }
}

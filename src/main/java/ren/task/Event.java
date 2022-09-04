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
        // Check if data is complete
        assert data.length == 5 : "data[] in readData in Event should be of size 5";

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
     * Compares this Event to another Task by their type of task.
     *
     * @param other The task to compare with.
     * @return 1 if the other task is not an Event, 0 if the other task is an Event.
     */
    @Override
    public int compareType(Task other) {
        if (other instanceof Todo) {
            return 1;
        } else if (other instanceof Deadline) {
            return 1;
        }
        return 0;
    }

    /**
     * Compares this Event to another Task by their date.
     *
     * @param other The task to compare with.
     * @return -1 if this task should be sorted first, 1 if the other task should be sorted first, 0 otherwise.
     */
    @Override
    public int compareDate(Task other) {
        if (other instanceof Todo) {
            return -1;
        } else if (other instanceof Deadline) {
            return this.start.compareTo(((Deadline) other).dateTime);
        } else if (other instanceof Event) {
            return this.start.compareTo(((Event) other).start);
        }
        return 0;
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

/**
 * Event Task represents an event that takes place between certain dates/time.
 */
public class Event extends Task {
    protected String dateTime;

    /**
     * Constructor for an Event Task.
     *
     * @param description the description of the Event
     * @param dateTime the start and end dates/time of the Event
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns an Event constructed with data read from File.
     *
     * @param data Data of an Event.
     * @return Event or null if data is corrupted.
     */
    public static Event readData(String[] data) {
        if (data.length < 4) {
            return null;
        }
        Event newEvent = new Event(data[2], data[3]);
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
        return "E|" + symbol + "|" + this.description + "|" + this.dateTime;
    }

    /**
     * Returns the string representation of this Event.
     *
     * @return String Representation.
     */
    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[E][" + symbol + "] " + this.description + "(at:" + this.dateTime + ")\n";
    }
}

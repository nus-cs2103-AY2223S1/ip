public class Event extends Task {
    private String datetime;

    /**
     * Constructor for Event instance
     * @param name Description of event
     * @param datetime Date/time of the event
     */
    public Event(String name, String datetime) {
        super(name);
        this.datetime = datetime;
    }

    /**
     * Returns the date/time of the event.
     * @return Date/time of the event
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * Sets the date/time of the event to the input date/time.
     * @param datetime New date/time of the event
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.datetime + ")";
    }
}

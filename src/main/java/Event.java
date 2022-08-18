public class Event extends Task {
    private String timing;

    /**
     * Constructor to create new Event
     * 
     * @param description Task description
     * @param timing      Timing of Event
     */
    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    /**
     * Factory method to create new Event
     * 
     * @param input String including task description and event timing specified
     *              after /at
     * @return new Event
     * @throws DukeException if event timing is not specified using /at
     */
    public static Event createEvent(String input) throws DukeException {
        if (input.indexOf("/at ") == -1)
            throw new DukeException("Please enter a valid event timing!");
        String eventDescription = input.split("/at ")[0];
        String event = input.split("/at ")[1];
        return new Event(eventDescription, event);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timing + ")";
    }
}

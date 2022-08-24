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
        this.timing = timing.trim();
    }

    /**
     * Constructor to create new Event with isDone
     * 
     * @param description Task description
     * @param timing      Timing of Event
     * @param isDone      Whether the task is done or not
     */
    public Event(String description, String timing, boolean isDone) {
        super(description, isDone);
        this.timing = timing.trim();
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
        if (input.indexOf("/at ") == -1) {
            throw new DukeException("Please enter a valid event timing using the /at flag.");
        }
        if (input.indexOf("/completed ") == -1) {
            String description = input.split("/at ")[0];
            String timing = input.split("/at ")[1];
            return new Event(description, timing);
        } else {
            boolean isDone = Boolean.parseBoolean(input.split("/completed ")[1]);
            input = input.split("/completed ")[0];
            String description = input.split("/at ")[0];
            String timing = input.split("/at ")[1];
            return new Event(description, timing, isDone);
        }
    }

    @Override
    public String getFileString() {
        return "event " + this.description + " /at " + this.timing + " /completed " + this.isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timing + ")";
    }
}

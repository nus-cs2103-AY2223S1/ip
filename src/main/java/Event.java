public class Event extends Task {

    private String timing;

    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    /**
     * Get the type of Task.
     *
     * @return "E" indicating Event.
     */
    public String getTaskType() {
        return "E";
    }

    /**
     * Get the String representation of an Event.
     *
     * @return String representation of an Event.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (at: " + this.timing + ")", this.getTaskType(), super.toString());
    }
}

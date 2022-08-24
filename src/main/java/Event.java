public class Event extends Task {
    private String timing;

    public Event(String taskDescription, String timing) {
        super(taskDescription);
        this.timing = timing;
    }

    /**
     * Format of parsing:
     * Type of task # status of task # description # timing
     * @return String that is in the parsing format.
     */
    @Override
    public String parse() {
        return String.format("E # %s # %s # %s", super.getStatusIcon(), super.getTaskDescription(), timing);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), timing);
    }
}

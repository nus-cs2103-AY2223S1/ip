public class Deadline extends Task {
    private String timing;

    public Deadline(String taskDescription, String timing) {
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
        return String.format("D # %s # %s # %s", super.getStatusIcon(), super.getTaskDescription(), timing);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), timing);
    }
}

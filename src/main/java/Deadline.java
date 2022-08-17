public class Deadline extends Task {
    private String timing;

    public Deadline(String taskDescription, String timing) {
        super(taskDescription);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), timing);
    }
}

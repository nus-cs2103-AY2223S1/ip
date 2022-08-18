public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        type = 'D';
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + deadline + ")";
    }
}

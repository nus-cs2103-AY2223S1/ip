public class Deadline extends Task {
    /**
     * The deadline of Deadline
     */
    protected String deadline;

    /**
     * Constructor for Deadline
     * @param title The title of Deadline
     * @param deadline The deadline of Deadline
     */
    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    @Override
    public String print() {
        return String.format("[D]%s (by: %s)", super.print(), this.deadline);
    }
}

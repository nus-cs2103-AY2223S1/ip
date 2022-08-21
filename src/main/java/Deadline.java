/**
 * The Deadline class represents a task
 * with a specific deadline.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructs a Deadline object.
     * @param description description for the deadline.
     * @param deadline string that represents deadline of task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(int i, String description, String deadline) {
        super(description);
        this.deadline = deadline;
        if (i == 1) {
            this.markDone();
        }
    }

    @Override
    public String toStore() {
        String status = super.isDone ? "1" : "0";
        String temp = "D" + " | " + status + " | " + super.description + " | " + this.deadline;
        return temp;
    }

    /**
     * Overriding method of toString() for Deadline.
     * @return the string representing Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + deadline + ")";
    }
}

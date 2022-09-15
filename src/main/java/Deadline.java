public class Deadline extends Task {
    private String deadline;

    /**
     * Constructor for the Deadline task.
     * @param name Input name of the task.
     */
    public Deadline(String name) {
        super(name);
        String[] split = name.split(" /by ");
        this.name = split[0];
        this.deadline = split.length < 2 ? "You didn't add a deadline!" : split[1];
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the Deadline task.
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatus(), name, deadline);
    }

    @Override
    public String changeFormat() {
        return String.format("D | %s | %s | %s", getStatus(), name, this.deadline);
    }
}

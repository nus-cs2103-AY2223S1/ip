public class Deadline extends Task {
    private String deadline;

    /**
     * Constructor for the Deadline task.
     * @param name Input name of the task.
     */
    public Deadline(String name) {
        super(name);
        String[] split = name.split("/by ");
        this.name = split[0];
        this.deadline = split.length < 2 ? "You didn't add a deadline!" : split[1];
    }

    /**
     * Returns a string representation of the Deadline task.
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", this.deadline);
    }
}

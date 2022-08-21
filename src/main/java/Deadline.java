public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline task.
     * @param description description of the task
     * @param by the deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Factory method to create a Deadline task from an encoded string.
     *
     * @param encodedInput the encoded entry with format deadline|description
     * @param completed the completion status of the Deadline task
     * @return the Deadline task
     */
    public static Deadline decode(String encodedInput, boolean completed) {
        String[] entries = encodedInput.split("\\|", 2);
        Deadline deadline = new Deadline(entries[1], entries[0]);
        deadline.setDone(completed);
        return deadline;
    }

    @Override
    public Type getType() {
        return Type.DEADLINE;
    }

    @Override
    public String encodeData() {
        return String.format("%s|%s", this.by, this.description);
    }

    @Override
    public String getDisplayText() {
        return String.format("%s (by: %s)", description, by);
    }
}

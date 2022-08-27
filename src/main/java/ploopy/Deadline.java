package ploopy;

public class Deadline extends Task {

    /**
     * {@inheritDoc}
     */
    public Deadline(String name, String dueDate) {
        super(name, dueDate);
        type = "D";
    }

    /**
     * {@inheritDoc}
     * Adds string representation of date.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), getDate());
    }
}

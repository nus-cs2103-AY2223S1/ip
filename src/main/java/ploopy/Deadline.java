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
     */
    @Override
    public String getDate() {
        return String.format("(by %s)", super.getDate());
    }
}

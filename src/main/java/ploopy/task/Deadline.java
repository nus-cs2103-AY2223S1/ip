package ploopy.task;

public class Deadline extends Task {

    /**
     * {@inheritDoc}
     */
    public Deadline(String name, String dueDate) {
        super(name, dueDate);
        type = "D";
    }

    @Override
    public String toString() {
        return String.format("%s %s%s", super.toString(), getDate(), getPriorityForString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDate() {
        return String.format("(by: %s)", super.getDate());
    }
}

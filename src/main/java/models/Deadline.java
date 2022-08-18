package models;

/**
 * A child class Deadline that inherits properties description and isDone from Task
 */
public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getSymbol() {
        return "T";
    }
    @Override
    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}

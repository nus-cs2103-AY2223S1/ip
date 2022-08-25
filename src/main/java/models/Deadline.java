package models;

public class Deadline extends Task {

    protected String by;
    protected DateFormatter dateFormatter;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        this.dateFormatter = new DateFormatter(by);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.dateFormatter);
    }
}
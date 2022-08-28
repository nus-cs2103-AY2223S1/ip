package duke.models;

public class Deadline extends Task {

    protected String by;
    protected FormattedDate formattedDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.formattedDate = new FormattedDate(by);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        this.formattedDate = new FormattedDate(by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.formattedDate);
    }
}
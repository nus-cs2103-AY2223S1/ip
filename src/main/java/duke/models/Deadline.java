package duke.models;

public class Deadline extends Task {
    protected formatDate date;

    public Deadline(String description, String by) {
        super(description);
        this.date = new formatDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.date + ")";
    }

    @Override
    public String toSave() {
        return "D" + super.toSave() + this.description + "| " + this.date;
    }
}

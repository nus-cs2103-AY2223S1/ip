package duke.models;

public class Deadline extends Task {
    protected FormatDate date;

    public Deadline(String description, String by) {
        super(description);
        this.date = new FormatDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.date + ")";
    }

    @Override
    public String toSave() {
        return "D" + super.toSave() + this.description + "| " + this.date;
    }

    public void snooze(String newDate) {
        this.date = new FormatDate(newDate);
    }
}

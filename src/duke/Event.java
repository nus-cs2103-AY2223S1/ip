package duke;

public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E] " + this.getStatusIcon() + " " + super.description + " (at:" + at + ")";
    }

    @Override
    public String toSaveVersion() {
        if (this.isDone()) {
            return "E|1|" + super.description + "|" + this.at + "\n";
        } else {
            return "E|0|" + super.description + "|" + this.at + "\n";
        }
    }
}

package betago.tasks;

public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getTaskDescription() + " (at: " + at + ")";
    }

    @Override
    public String saveTask() {
        String icon;
        if (this.getStatusIcon() == "X") {
            icon = "1";
        } else {
            icon = "0";
        }
        return "E , " + icon + " , " + this.description + " , " + this.at + "\n";
    }
}

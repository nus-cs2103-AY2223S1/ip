

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getTaskDescription() + " (by: " + by + ")";
    }

    @Override
    public String saveTask() {
        String icon;
        if (this.getStatusIcon() == "X") {
            icon = "1";
        } else {
            icon = "0";
        }
        return "D , " + icon + " , " + this.description + " , " + this.by + "\n";
    }
}

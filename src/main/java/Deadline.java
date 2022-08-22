public class Deadline extends Task {
    private String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }

    @Override
    public String toSave() {
        return "D" + Task.SAVE_SEPARATOR + this.getIsDoneString() + Task.SAVE_SEPARATOR + this.getDescription()
                + Task.SAVE_SEPARATOR + this.time;
    }
}

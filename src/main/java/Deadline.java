public class Deadline extends Task {
    protected String time;
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "[D]" + s + " (by: " + time +")";
    }

    public String toFile() {
        String s = super.toFile();
        return "D," + s + "," + time;
    }
}
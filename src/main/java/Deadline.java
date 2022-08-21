public class Deadline extends Task {
    protected String time;
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }
    @Override
    public String toString() {
        String s = super.toString();
        return "[D]" + s + " (by: " + time +")";
    }
}
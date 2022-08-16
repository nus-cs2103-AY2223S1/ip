public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X" : " ", description, time);
    }
}

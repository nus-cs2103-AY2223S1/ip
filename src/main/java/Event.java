public class Event extends Task {
    private String time;

    public Event(boolean isDone, String text, String time) {
        super(isDone, text);
        this.time = time;
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.time);
    }
}
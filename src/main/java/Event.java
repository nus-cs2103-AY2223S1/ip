public class Event extends Task {
    public String time;

    public Event(String title, String time) {
        super(title);
        this.time = time;
    }

    @Override
    public String print() {
        return String.format("[E]%s (at: %s)", super.print(), this.time);
    }
}

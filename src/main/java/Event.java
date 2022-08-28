public class Event extends Task{

    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at:%s)", super.toString(), this.duration);
    }
}

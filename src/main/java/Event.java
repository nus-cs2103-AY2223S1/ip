public class Event extends Task {
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public Event(String description, boolean isDone ,String duration) {
        super(description, isDone);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " ( at: " + duration + " )";
    }

    @Override
    public String toStorageFormat() {
        return "E | " + super.toStorageFormat() + " | " + duration;
    }

}

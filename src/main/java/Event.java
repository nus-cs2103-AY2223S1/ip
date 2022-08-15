public class Event extends Task{
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        taskIncrementer();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}

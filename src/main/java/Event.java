public class Event extends Task{
    private String at;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String saveStringFormat() {
        return String.format("E | %s | %s", super.saveStringFormat(), at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

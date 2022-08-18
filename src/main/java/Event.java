public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        String color = (isDone ? ANSI_GREEN : ANSI_RED);
        return color + "[E]" + super.toString() + " (at: " + at + ")" + ANSI_RESET;
    }
}

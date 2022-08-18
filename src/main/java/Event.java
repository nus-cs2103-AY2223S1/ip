public class Event extends Task {

    protected String at;
    private final static String ICON = "E";

    public Event(String description, String at) {
        super(description, ICON);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]" + "[%s] " + super.toString() + " (at: " + at + ")", super.getStatusIcon());
    }
}

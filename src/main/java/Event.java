public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        String parStr = super.toString();
        return String.format("[E]%s (at: %s)", parStr, getAt());
    }
}

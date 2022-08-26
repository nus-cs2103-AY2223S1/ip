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
    protected String getStorageString() {
        String parStr = super.getStorageString();
        return String.format("%s|%s|%s", "E", parStr, getAt());
    }

    @Override
    public String toString() {
        String parStr = super.toString();
        return String.format("[E]%s (at: %s)", parStr, getAt());
    }
}

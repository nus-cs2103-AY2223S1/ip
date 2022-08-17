public class Event extends Task {
    private String at;

    Event(String description, boolean isComplete, String at) {
        super(description, isComplete);
        this.at = at;
    }

    Event(String description, String at) {
        this(description, false, at);
    }

    String getDate() {
        return this.at;
    }

    @Override
    String toStorageFormat() {
        return "E | " + super.toStorageFormat() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + at + ")";
    }
}

public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return "E" + Task.STORAGE_DELIMITER + taskString + Task.STORAGE_DELIMITER + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

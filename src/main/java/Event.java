public class Event extends Task {
    protected String date;
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: " + this.date + ")");
    }

    @Override
    public String toStorageString() {
        String doneDescriptionString = super.toStorageString();
        return "E" + Task.STORAGE_DELIMITER
                + doneDescriptionString + Task.STORAGE_DELIMITER
                + this.date;
    }
}
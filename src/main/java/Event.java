public class Event extends Task {
    protected String type = "[E]";
    protected String dateTime;
    Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;

    }

    @Override
    public String toString() {
        return type + super.toString() + "(at: " + dateTime + ")";
    }

    @Override
    public String toFile() {
        String isDone = "0";
        if (super.isDone) {
            isDone = "1";
        }
        return String.format("E|%s|%s|%s\n", isDone, super.description, dateTime);
    }
}

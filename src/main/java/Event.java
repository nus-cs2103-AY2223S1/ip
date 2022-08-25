public class Event extends Task {
    private String during;
    private String time;

    public Event(ParsedData parsedData) {
        super(parsedData);
        this.during = parsedData.getDuring();
        this.time = parsedData.getTime();
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        String result = this.getTypeIcon() + this.getStatusIcon() + this.taskName
                + " (" + this.during + ": " + this.time + ")";
        return result;
    }
}

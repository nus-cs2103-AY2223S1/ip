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
    public String getTypeLetter() {
        return "E";
    }

    @Override
    public String getDuring() {
        return this.during;
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s (%s: %s)",
                this.getTypeIcon(),
                this.getStatusIcon(),
                this.description,
                this.during,
                this.time);
    }
}

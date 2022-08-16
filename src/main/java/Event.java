public class Event extends Task{
    protected String dateTime;

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[E][" + symbol + "] " + this.description + "(at:" + this.dateTime + ")\n";
    }
}

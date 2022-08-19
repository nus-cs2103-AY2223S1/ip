public class Event extends Task {
    protected String date;
    public Event(String description, String date, String taskType) {
        super(description, taskType);
        this.date = date;
    }
    public String getDate() {
        return date;
    }
}

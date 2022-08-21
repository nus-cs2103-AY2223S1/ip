public class Event extends Task {
    private String date;

    public Event(String task, String date) {
        super(task);
        this.date = date;
    }
    public Event(String task, String date, boolean isDone) {
        super(task, isDone);
        this.date = date;
    }
    @Override
    public String toPrint() {
        return "E" + super.toPrint() + " | " + date;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}

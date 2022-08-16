public class Event extends Task {
    String date;

    Event(String task_description, String date) {
        super(task_description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}

public class Event extends Task {
    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E][%s]%s(at:%s)", this.isDone ? "X" : " ", this.description, this.date);
    }
}

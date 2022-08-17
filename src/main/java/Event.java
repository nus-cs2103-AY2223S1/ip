public class Event extends Task {
    private String date;

    public Event(String msg, String date) {
        super(msg);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s%s (at: %s)", "[E]", super.toString(), this.date);
    }

}

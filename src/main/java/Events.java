public class Events extends Task {

    private final String date;
    public Events(String event, String date) {
        super(event);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + date + ")";
    }
}

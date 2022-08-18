public class Event extends Task {

    private String time;

    public Event(String description, String time) {
        super(description, "E");
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.time);
    }

}

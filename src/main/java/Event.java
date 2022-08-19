public class Event extends Task {

    private String time;

    Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        String out = "[E]";
        out += super.toString();
        out += " (at: " + this.time + ")";
        return out;
    }
}

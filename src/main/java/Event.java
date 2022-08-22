public class Event extends Task {
    private String time;
    private String name;
    public Event(String name,String time) {
        super(name);
        this.time = time;
        this.name = name;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }

    public String parse() {
        if (this.getCompleted()) {
            return "E#1#" + this.name + "#" + this.time;
        } else {
            return "E#0#" + this.name + "#" + this.time;
        }

    }
}

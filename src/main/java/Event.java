public class Event extends Task {

    private String time;

    public Event(String name, String time) {
        super(name, "E");
        this.time = time;
    }

    @Override
    public String output() {
        return super.output() + this.time;
    }
}

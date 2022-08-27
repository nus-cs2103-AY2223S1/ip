public class Event extends Task{

    protected String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String fileFormat() {
        return "E" + super.fileFormat() + "|" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
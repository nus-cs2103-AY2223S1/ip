public class Events extends Task{

    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTime() {
        return at;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

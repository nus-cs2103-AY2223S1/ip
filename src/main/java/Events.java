public class Events extends Task{
    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    public Events() throws DokeException {
        throw new DokeException("Events");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

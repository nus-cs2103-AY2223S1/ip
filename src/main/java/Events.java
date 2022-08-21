public class Events extends Task{
    protected String at;

    public Events(String description, String at) {
        super(description, "E");
        this.at = at;
    }

    public Events(String description, String done, String at) {
        super(description, done, "E");
        this.at = at;
    }

    public String getDateline() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

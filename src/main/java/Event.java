public class Event extends Task {
    String at;

    public Event(String description, boolean done, String at) {
        super(description, done);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public char getType()
    {
        return 'E';
    }

    @Override
    public String getDetail()
    {
        return at;
    }
}
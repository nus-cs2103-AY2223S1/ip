public class Event extends Task {

    protected String by;

    public Event(String task, String at) {
        super(task);
        this.by = at;
    }

    protected String getBy() {
        return by;
    }

    protected char getType() {
        return 'E';
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + by.trim() + ")";
    }
}

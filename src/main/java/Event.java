public class Event extends Task {
    private String at;

    public Event(String name, String at) {
        super(name, 'E');
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + " (at: %s)", at);
    }
}

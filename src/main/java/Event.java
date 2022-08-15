public class Event extends Task {
    private String startEnd;

    public Event(String taskString, String startEnd) {
        super(taskString);
        if (startEnd.isBlank()) {
            throw new IllegalArgumentException("Time of event cannot be blank.");
        }
        this.startEnd = startEnd;
    }

    @Override
    public char getChar() {
        return 'E';
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.startEnd + ")";
    }
}

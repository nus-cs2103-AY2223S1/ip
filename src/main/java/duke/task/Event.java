package duke.task;

public class Event extends Task {
    private final String duration;

    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    public Event(String name, String duration, boolean isDone) {
        super(name, isDone);
        this.duration = duration;
    }

    public static Event fromString(String inputString) {
        boolean isDone = inputString.charAt(4) == 'X';
        String name = inputString.substring(7, inputString.indexOf("(at"));
        String duration = inputString.substring(inputString.indexOf("(at: ") + 5, inputString.length() - 1);
        return new Event(name, duration, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.duration + ")";
    }
}

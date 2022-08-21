import exceptions.TaskDescriptionEmpty;

public class Event extends Task {
    private static final String typeIcon = "E";
    private String time;

    protected Event(boolean isDone, String description, String time) throws TaskDescriptionEmpty {
        super(isDone, description);
        this.time = time;
    }

    public static Event create(String description, String time) throws TaskDescriptionEmpty {
        return new Event(false, description, time);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", typeIcon, super.toString(), this.time);
    }
}

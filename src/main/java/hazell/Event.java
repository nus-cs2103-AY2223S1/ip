package hazell;

import hazell.exceptions.TaskDescriptionEmpty;

public class Event extends Task {
    private static final String typeIcon = "E";
    private String time;

    protected Event(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    public static Event create(String description, String time) throws TaskDescriptionEmpty {
        Event event = new Event(false, description, time);
        event.validate();
        return event;
    }

    public String serialise() {
        return String.format("%s | %s | %s | %s",
                typeIcon,
                this.getIsDone() ? 1 : 0,
                this.getDescription(),
                this.time);
    }

    public static Event unserialise(String[] words) {
        return new Event(
                words[1].equals("1"),
                words[2],
                words[3]);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", typeIcon, super.toString(), this.time);
    }
}

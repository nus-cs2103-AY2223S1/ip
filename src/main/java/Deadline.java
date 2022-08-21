import exceptions.TaskDescriptionEmpty;

public class Deadline extends Task {
    private static final String typeIcon = "D";
    private String time;

    protected Deadline(boolean isDone, String description, String time) throws TaskDescriptionEmpty {
        super(isDone, description);
        this.time = time;
    }

    public static Deadline create(String description, String time) throws TaskDescriptionEmpty {
        return new Deadline(false, description, time);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", typeIcon, super.toString(), this.time);
    }
}

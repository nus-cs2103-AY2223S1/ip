import exceptions.TaskDescriptionEmpty;

public class Deadline extends Task {
    private static final String typeIcon = "D";
    private String time;

    protected Deadline(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    public static Deadline create(String description, String time) throws TaskDescriptionEmpty {
        Deadline deadline = new Deadline(false, description, time);
        deadline.validate();
        return deadline;
    }

    public String serialise() {
        return String.format("%s | %s | %s | %s",
                typeIcon,
                this.getIsDone() ? 1 : 0,
                this.getDescription(),
                this.time);
    }

    public static Deadline unserialise(String[] words) {
        return new Deadline(
                words[1] == "1",
                words[2],
                words[3]);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", typeIcon, super.toString(), this.time);
    }
}

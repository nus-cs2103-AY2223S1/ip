public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String color = (isDone ? ANSI_GREEN : ANSI_RED);
        return color + "[D]" + super.toString() + " (by: " + by + ")" + ANSI_RESET;
    }

    @Override
    public String toFile() {
        int done = (isDone ? 1 : 0);
        return String.format("D | %d | %s | %s", done, description, by);
    }
}

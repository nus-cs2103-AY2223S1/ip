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
}

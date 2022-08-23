public class Deadline extends Task {
    private String by;

    public Deadline(String desc, String by) throws MissingDescriptionException, MissingTimeException {
        super(desc);
        if (desc.isBlank()) {
            throw new MissingDescriptionException(Duke.commandGuide("deadline", Command.DEADLINE));
        } else if (by.isBlank()) {
            throw new MissingTimeException(Duke.commandGuide("deadline", Command.DEADLINE));
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

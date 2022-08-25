public class Deadline extends Task{
    private String during;
    private String time;

    public Deadline(ParsedData parsedInput) {
        super(parsedInput);
        this.during = parsedInput.getDuring();
        this.time = parsedInput.getTimeText();
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        String result = this.getTypeIcon() + this.getStatusIcon() + this.taskName
                + " (" + this.during + ": " + this.time + ")";
        return result;
    }
}

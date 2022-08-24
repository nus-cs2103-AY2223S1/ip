public class Deadline extends Task {
    protected String type = "[D]";
    protected String dateTime;

    Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return type + super.toString() + "(by:" + dateTime + ")";
    }

    @Override
    public String toFile() {
        String isDone = "0";
        if (super.isDone) {
            isDone = "1";
        }
        return String.format("D|%s|%s|%s\n", isDone, super.description, dateTime);
    }
}

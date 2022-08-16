public class Deadline extends Task{
    protected String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[D][" + symbol + "] " + this.description + "(by:" + this.dateTime + ")\n";
    }
}

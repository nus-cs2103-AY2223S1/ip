public class Event extends Task {
    protected String timeQualifier;
    protected String timeDescription;


    Event(String description, String timeQualifier, String timeDescription) {
        super(description);
        this.timeQualifier = timeQualifier;
        this.timeDescription = timeDescription;
    }

    public String getDeadlineStatusIcon() {
        return "E";
    }

    @Override
    public String toWrite() {
        return "D," + (super.isDone ? "1," : "0,") + super.description + "," + timeQualifier + "," + timeDescription + "\n";
    }

    @Override
    public String toString() {
        return "[" + getDeadlineStatusIcon() + "]" + super.toString() + " (" + this.timeQualifier + ": " + this.timeDescription + ")";
    }
}

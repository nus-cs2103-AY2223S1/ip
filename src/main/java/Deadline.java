public class Deadline extends Task {

    protected String timeQualifier;
    protected String timeDescription;


    Deadline(String description, String timeQualifier, String timeDescription) {
        super(description);
        this.timeQualifier = timeQualifier;
        this.timeDescription = timeDescription;
    }

    public String getDeadlineStatusIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + getDeadlineStatusIcon() + "]" + super.toString() + " (" + this.timeQualifier + ": " + this.timeDescription + ")";
    }
}

public class Deadline extends Task {
    protected String dateTime;

    public Deadline(String dateTime, String description) {
        super(description, "D");
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return " (by: " + this.dateTime + ")";
    }

    @Override
    public String printText() {
        return super.printText() + " | " + this.dateTime;
    }

    @Override
    public String toString() {
        return "[" + this.getCode() + "]" + super.toString() + this.getDateTime();
    }
}
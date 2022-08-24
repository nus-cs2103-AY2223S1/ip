public class Event extends Task {

    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getLetterTag() {
        return "E";
    }

    @Override
    public String getAdditionalInfo() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[" + this.getLetterTag() + "][" + this.getStatusIcon() + "] "
                + this.description + " (at: " + this.time + ")";
    }

}

package duke.models;

public class Event extends Task {

    protected String at;
    protected FormattedDate formattedDate;
    protected boolean isRecurring;
    protected Interval interval = Interval.None;

    public enum Interval {
        Day,
        Week,
        Month,
        None;

        @Override
        public String toString() {
            switch(this) {
                case Day: return "D";
                case Week: return "W";
                case Month: return "M";
                case None: return " ";
                default: throw new IllegalArgumentException();
            }
        }
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.formattedDate = new FormattedDate(at);
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
        this.formattedDate = new FormattedDate(at);
    }

    public Event(String description, boolean isDone, String at, Interval interval) {
        super(description, isDone);
        this.at = at;
        this.formattedDate = new FormattedDate(at);
        this.interval = interval;
    }

    public void setRecurring(Interval interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return String.format("[E][%s]%s (at: %s)", this.interval, super.toString(), this.formattedDate);
    }
}
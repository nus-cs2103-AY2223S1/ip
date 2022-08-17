public class Event extends Task {
    private static final String NO_TIMERANGE_MESSAGE = "An Event-type Task must be provided with a time range. Use the /at parameter to add a time range.";

    protected String timeRange;

    Event(String desc, String timeRange) throws DukeException {
        super(desc);
        if (timeRange == null || timeRange.equals("")) {
            throw new DukeException(Event.NO_TIMERANGE_MESSAGE);
        }
        this.timeRange = timeRange;
    }

    Event(String desc, String timeRange, boolean isDone) throws DukeException {
        super(desc, isDone);
        if (timeRange == null || timeRange.equals("")) {
            throw new DukeException(Event.NO_TIMERANGE_MESSAGE);
        }
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timeRange);
    }

}

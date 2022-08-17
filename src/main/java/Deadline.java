public class Deadline extends Task {
    private static final String NO_ENDTIME_MESSAGE = "A Deadline-type Task must be provided with an ending time. Use the /by parameter to add a deadline.";

    protected String endTime;

    Deadline(String desc, String endTime) throws DukeException {
        super(desc);
        if (endTime == null || endTime.equals("")) {
            throw new DukeException(NO_ENDTIME_MESSAGE);
        }
        this.endTime = endTime;
    }

    Deadline(String desc, String endTime, boolean isDone) throws DukeException {
        super(desc, isDone);
        if (endTime == null || endTime.equals("")) {
            throw new DukeException(NO_ENDTIME_MESSAGE);
        }
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.endTime);
    }

}

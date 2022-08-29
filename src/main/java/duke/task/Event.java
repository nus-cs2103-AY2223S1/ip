package duke.task;

import duke.exception.UnexpectedDateTimeFormatException;

public class Event extends ScheduleTask {

    public Event(String description, String at) throws UnexpectedDateTimeFormatException {
        super(description, at);
    }

    public Event(String description, String at, boolean isDone) {
        super(description, at, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + showDateTime() + ")";
    }
}

package duke.task;

//import exception
import duke.exception.UnexpectedDateTimeFormatException;

public class Deadline extends ScheduleTask {

    public Deadline(String description, String by) throws UnexpectedDateTimeFormatException {
        super(description, by);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, by, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + showDateTime() + ")";
    }
    
}

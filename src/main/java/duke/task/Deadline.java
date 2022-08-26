package duke.task;

import duke.main.DateTimeFormatUtils;
import java.time.LocalDateTime;

/**
 * Class handling the deadline task type.
 */
public class Deadline extends Task {
    /* Time frame that the Deadline object should be completed by*/
    protected LocalDateTime deadlineDate;

    /**
     * Constructor for Deadline Class.
     *
     * @param name String representation of task name.
     * @param deadlineDate LocalDateTime representation of task deadline.
     */
    public Deadline(String name, LocalDateTime deadlineDate) {
        this.name = name;
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns string representation of Deadline object.
     *
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeFormatUtils.printDate(deadlineDate) + ")";
    }

    /**
     * Returns formatted string representation of deadline task for save processing.
     *
     * @return Formatted string representation of deadline task.
     */
    @Override
    public String convertToSaveFormat() {
        int status = isDone ? 1 : 0;
        return String.format("%s | %d | %s | %s", "D", status, name,
                DateTimeFormatUtils.convertToInputFormat(deadlineDate));
    }
}

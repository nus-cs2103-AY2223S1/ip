import java.time.LocalDateTime;

public class Deadline extends Task {
    /* Time frame that the Deadline object should be completed by*/
    protected LocalDateTime deadlineDate;

    /**
     * Constructor for Deadline Class.
     * @param name String representation of task name.
     * @param deadlineDate LocalDateTime representation of task deadline.
     */
    public Deadline(String name, LocalDateTime deadlineDate) {
        super(name);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns string representation of Deadline object.
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeFormatUtils.printDate(deadlineDate) + ")";
    }

}

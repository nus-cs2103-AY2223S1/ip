package duke;
import java.time.LocalDate;

/**
 * Class for tasks that have a deadline.
 */
public class Deadline extends Task {
    private static final String TYPE = "[D]";
    private LocalDate date;

    /**
     * Constructor for new deadline instance.
     *
     * @param name name of task.
     * @param date date of deadline, in LocalDate TYPE.
     * @throws MissingDescriptionException missing name.
     */
    public Deadline(String name, LocalDate date) throws MissingDescriptionException {
        super(name);
        this.date = date;
    }

    /**
     * Returns string representation of task.
     *
     * @return string with TYPE, completed or not, name, and date time(if applicable).
     */
    @Override
    public String toString() {
        String comp = this.isCompleted
                ? "[X]"
                : "[ ]";
        String dateString = date.getDayOfMonth() + " " + date.getMonth().toString() + " " + date.getYear();
        String priorityLevel = this.priority + " priority";
        return TYPE + comp + name + dateString + " " + priorityLevel.toLowerCase();
    }

    /**
     * Returns string representation of task to be written in text file.
     *
     * @return string representation to be written in text file.
     */
    @Override
    public String toData() {
        String type = "D";
        String completed = this.isCompleted ? "1" : "0";
        String dateString = date.toString();
        return type + FIELD_DIVIDER + completed + FIELD_DIVIDER + name + FIELD_DIVIDER + priority
                + FIELD_DIVIDER + dateString;
    }

}

package duke;
import java.time.LocalDate;

/**
 * class for tasks that have a deadline
 */
public class Deadline extends Task {
    private static final String type = "[D]";
    private LocalDate date;

    /**
     * constructor for new deadline instance
     * @param name name of task
     * @param date date of deadline, in LocalDate type
     * @throws MissingDescriptionException missing name
     */
    public Deadline(String name, LocalDate date) throws MissingDescriptionException {
        super(name);
        this.date = date;
    }

    /**
     * returns string representation of task.
     * @return string with type, completed or not, name, and date time(if applicable)
     */
    @Override
    public String toString() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        String dateString = date.getDayOfMonth() + " " + date.getMonth().toString() + " " + date.getYear();
        return type + comp + name + dateString;
    }

    /**
     * return string representation of task to be written in text file
     * @return string representation to be written in text file
     */
    @Override
    public String toData() {
        String type = "D";
        String completed = this.completed ? "1" : "0";
        String dt = date.toString();
        return type + "//" + completed +"//" + name + "//" + dt;
    }

}

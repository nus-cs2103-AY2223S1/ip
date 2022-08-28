package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task which needs to be completed by a certain date.
 */
public class DeadLine extends Task {
    private LocalDate byWhen = null;

    public DeadLine() {
        super();
    }

    /**
     * Adds the description and the deadline of a task.
     * The userInput argument must contain a description of the deadline, followed by a
     * date in the format yyyy-mm-dd or yyyy/mm/dd.
     * <p>
     * If the date format is not recognised, or the description is empty, the function will throw a DukeException.
     *
     * @param userInput a String containing the description and deadline of a task
     * @throws DukeException
     */
    public void addName(String userInput) throws DukeException {
        if (userInput.length() <= 9) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }

        int index = userInput.indexOf("/by") - 1;

        if (index <= 8) {
            throw new DukeException("OOPS!!! Please indicate when the deadline is due with '/by'.");
        }

        super.addName(userInput.substring(9, index));

        this.byWhen = LocalDate.parse(userInput.substring(index + 5).replace('/', '-'));;
    }

    /**
     * Returns a String in a format that will be stored in the specified directory.
     * The String will contain the type of task, the current status (marked or unmarked) of the task and
     * the description of the task.
     *
     * @return the details of the task for Storage
     */
    @Override
    public String getTask() {
        return String.format("D | " + super.getTask() + " | " + this.byWhen);
    }

    /**
     * Returns a String that will display the task.
     * The String will contain the type of task, the current status (marked or unmarked) of
     * the task and the description of the task.
     *
     * @return the details of the task for display
     */
    @Override
    public String getStatus() {
        return String.format("[D]%s (by: %s)", super.getStatus(),
                this.byWhen.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}

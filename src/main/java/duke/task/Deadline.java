package duke.task;

import duke.main.DateTime;

import java.time.LocalDateTime;

/**
 * Class for Deadline-type Tasks.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for the Deadline task.
     * @param name Input name of the task.
     */
    public Deadline(String name, LocalDateTime dateTime) {
        super(name);
        this.deadline = dateTime;
    }

    /**
     * Returns a string representation of the Deadline task.
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", DateTime.printDate(deadline));
    }

    /**
     * Returns the format of Deadline object in format to be saved.
     * @return String of Deadline object to be saved.
     */
    @Override
    public String changeFormat() {
        return String.format("D | %s | %s | %s", getStatus(), name, DateTime.changeFormat(this.deadline));
    }
}

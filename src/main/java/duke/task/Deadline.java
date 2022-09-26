package duke.task;

import duke.main.DateTime;

import java.time.LocalDateTime;

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

    @Override
    public String changeFormat() {
        return String.format("D | %s | %s | %s", getStatus(), name, DateTime.changeFormat(this.deadline));
    }
}

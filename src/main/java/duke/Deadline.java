package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates all tasks which must be done by a specified deadline.
 */
class Deadline extends Task {

    LocalDate deadline;

    /**
     * Creates a new Deadline task.
     * @param name The task to be completed.
     * @param deadline The deadline by which the task must be completed.
     */
    Deadline (String name, String deadline) {
        super(name);
        this.deadline = LocalDate.parse(deadline.trim());
    }

    /**
     * Creates a new Deadline task.
     * @param name The task to be completed.
     * @param deadline The deadline by which the task must be completed.
     * @param done The status of the task.
     */
    Deadline (String name, String deadline, boolean done) {
        super(name, done);
        this.deadline = LocalDate.parse(deadline.trim(), DateTimeFormatter.ofPattern("MMM dd yyyy"));
        assert(this.deadline.isAfter(LocalDate.now()));
    }

    @Override
    public String toString() {
        String out = "[D][";
        if (super.getStatus()) {
            out += "X";
        } else {
            out += " ";
        }
        out += "] " + super.toString() + "(by : " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return out;
    }


}

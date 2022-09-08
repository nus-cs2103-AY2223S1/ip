package duke.task;

import duke.Date;

/**
 * Creates a Task object to be done by a specific time.
 * @author Jason
 */
public class Deadline extends Task {

    /**
     * Constructs a Deadline object.
     * @param description Description of the Deadline object.
     * @param by Date which the Deadline object is due.
     */
    public Deadline(String description, Date by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

    /**
     * Writes this Deadline task into the save file format.
     * @return String to be stored in save file.
     */
    @Override
    public String saveData() {
        String marked = this.isDone ? "1" : "0";
        return "D | " + marked + " | " + this.description + " | " + this.date;
    }
}

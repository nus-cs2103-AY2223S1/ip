package duke.task;

import duke.Date;

/**
 * Creates a Task object that needs to be done.
 * @author Jason
 */
public class Todo extends Task {

    /**
     * Constructs a to do object, with an arbitrary date.
     * @param description Description of the to do object.
     * @param date Date is set to the latest date possible since todos do not require dates.
     */
    public Todo(String description, Date date) {
        super(description, date);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Writes this to do task into the save file format.
     * @return String to be stored in save file.
     */
    @Override
    public String saveData() {
        String marked = this.isDone ? "1" : "0";
        return "T | " + marked + " | " + this.description;
    }
}

package duke.task;

import duke.dukeexception.DukeException;

/**
 * A class represents task with deadline.
 */
public class DeadLine extends Task {
    private String by;
    private String description;

    /**
     * A class constructor
     * @param description Full version of the input
     * @throws DukeException Throws when error occurs
     */
    public DeadLine(String description) throws DukeException {
        super("tempTask");
        try {
            String temp=description.split(" ")[1];
        } catch (IndexOutOfBoundsException ie) {
            throw (new DukeException("OOPS!!! The description of a deadline cannot be empty."));
        } try {
            super.correctDescrition(description.split("by")[0].split(" ", 2)[1]);
            super.getFullDescription(description.split(" ",2)[1]);
            this.by = description.split("by")[1];
        } catch (IndexOutOfBoundsException ie) {
            throw (new DukeException("OOPS!!! The description of a deadline is still not correct."));
        }
        this.description = description;
    }

    /**
     * Print the right format of the task when required to show user the task content.
     * If task comes with a date format, then will print the time as "MMM: DD YYYY".
     * @return A string containing formatted description of the task.
     */
    @Override
    public String printTask() {
        try {
            return "[D]" + super.printTask() + " (by:" + super.showTime() + ")";
        } catch (DukeException d) {
            return "[D]" + super.printTask() + " (by:" + this.by + ")";
        }
    }

    @Override
    public String getDescription(){
        return this.description;
    }
}

package duke.task;

import duke.dukeexception.DukeException;

/**
 * Represents the task of event format.
 */
public class Event extends Task {
    private String at;
    private String description;

    /**
     * class constructor
     * @param description full version of the input
     * @throws DukeException throws expection when error occurs
     */
    public Event(String description) throws DukeException {
        super("tempTask");
        try {
            String temp = description.split(" ")[1];
        } catch (IndexOutOfBoundsException ie) {
            throw (new DukeException("OOPS!!! The description of a event cannot be empty."));
        }
        try {
            super.correctDescrition(description.split("on",2)[0].split(" ", 2)[1]);
            super.getFullDescription(description.split(" ",2)[1]);

            this.at = description.split("on",2)[1];
        } catch (Exception ie) {
            throw(new DukeException("OOPS!!! The description of a event is still not correct."));
        }
        this.description = description;

    }
    /**
     * Print the right format of the task when required to show user the task content.
     * If task comes with a date format, then will print the time as "MMM: DD YYYY".
     * @return A string containing formatted description of the task.
     */
    @Override
    public String printTask(){
        try {
            return "[E]" + super.printTask() + " (at:"
                    + super.showTime() + ")";
        }catch (DukeException d){
            return "[E]" + super.printTask() + " (at:"
                    + this.at + ")";
        }
    }
    @Override
    public String getDescription(){
        return this.description;
    }
}

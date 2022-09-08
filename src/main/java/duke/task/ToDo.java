package duke.task;

import duke.dukeexception.DukeException;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {
    private String description;
    public ToDo(String description) throws DukeException {
        super("tempTask");
        try {
            String temp = description.split(" ")[1];
        } catch (IndexOutOfBoundsException ie) {
            throw (new DukeException("OOPS!!! The description of a todo cannot be empty."));
        }
        try {
            super.correctDescrition(description.split(" ", 2)[1]);
        } catch (IndexOutOfBoundsException ie) {
            throw (new DukeException("OOPS!!! The description of a todo is still not complete."));
        }
        this.description = description;
    }
    /**
     * @return A string containing the task in a printable format
     */
    @Override
    public String printTask(){
        return "[T]"+super.printTask();
    }
    @Override
    public String getDescription(){
        return this.description;
    }
}

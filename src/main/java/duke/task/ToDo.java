package duke.task;

import duke.dukeexception.DukeException;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {
    private String description;

    /**
     * Class constructor
     * @param description A line of user input.
     * @throws DukeException Throws exception when the description is wrong.
     */
    public ToDo(String description) throws DukeException {
        super("tempTask");
        try {
            String temp = description.split(" ")[1];
        } catch (IndexOutOfBoundsException ie) {
            throw (new DukeException("Traveller, the description of a todo cannot be empty."));
        }
        try {
            super.correctDescrition(description.split(" ",  2)[1]);
        } catch (IndexOutOfBoundsException ie) {
            throw (new DukeException("Traveller, the description of a todo is still not complete."));
        }
        int todoDescriptionLength = description.split(" ").length;
        assert todoDescriptionLength > 1 : "todo description should have 2 words or more";
        this.description = description;
    }
    /**
     * @return A string containing the task in a printable format
     */
    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }
    @Override
    public String getDescription() {
        return this.description;
    }
}

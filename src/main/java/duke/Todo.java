package duke;

/**
 * This class is responsible for Todos
 *
 * @author Kang Zong Xian
 */
public class Todo extends Task {

    /**
     * The constructor, does not have a time attribute
     * @param description the description of the task to do
     */
    public Todo(String description) throws DukeException {
        super(description);
        if (description.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * The string representation of the to-do task
     * @return a string representing the description of the to-do
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * A string representation to write to the file
     * @return a string that represents what to write to the file
     */
    @Override
    public String saveToDisk() {
        return "[T]" + super.toString();
    }

}

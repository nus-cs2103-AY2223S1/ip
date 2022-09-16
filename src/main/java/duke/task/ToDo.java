package duke.task;

/**
 * Class which inherits the Task class for a ToDo
 *
 * @author kaij77
 * @version 0.1
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String stringifyTask() {
        return String.format("%s | %s", "T", super.stringifyTask());
    }

    /**
     * Returns the String representation of the ToDo.
     *
     * @return the String representation of the ToDo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

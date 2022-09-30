package duke.listobjects;


/**
 * Represents ToDo which is a listObject with only a task decription
 */
public class ToDo extends ListObject {

    /**
     * Constructs a ToDo object with given task description and status with default deadline of 4000-12-31 so user can
     * later choose to modify time argument and add a deadline
     *
     * @param task   String representing task description
     * @param status int with value 1 if task is complete and 0 otherwise
     */
    public ToDo(String task, int status) {
        super(task, "4000-12-31", status);
    }


    /**
     * Returns String representing the ToDo object
     *
     * @return String representing ToDo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

package dukeprogram;

/**
 * Todo is a type of Task that only possess a name and status of the task
 */
public class ToDo extends Task {

    /**
     * Creates a Todo task with the given name
     * @param name name of the todo task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string with the todo tag, followed by task data
     * @return a string in the format "[T][status] task_name"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

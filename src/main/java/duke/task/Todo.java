package duke.task;

/**
 * Encapsulates a Todo
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo
     *
     * @param name Name / Description of the task
     */
    public Todo(String name) {
        super(name, 'T');
    }

    /**
     * Returns string representation of the todo consisting of the string representation of Todo, [T], the completion
     * status of the todo and the todo description
     * */
    @Override
    public String toString() {
        return super.toString();
    }
}

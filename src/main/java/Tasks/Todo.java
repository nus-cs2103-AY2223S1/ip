package Tasks;

/**
 * Creates a new todo task from the todo command
 */
public class Todo extends Task {

    /**
     * Constructor that creates a new todo task with the title of task input by the user
     * @param msg
     */
    public Todo(String msg) {
        super(msg);
    }

    /**
     * Prints the correct output for each todo task
     *
     * @return string of final output to be printed in the UI
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

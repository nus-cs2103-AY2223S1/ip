package roger.tasks;

import roger.tasks.Task;

public class ToDo extends Task {

    public ToDo(String name) {
        /**
         * Constructor for ToDo class. Sets the ToDo name.
         *
         * @param name The ToDo name.
         */
        super(name);
    }

    /**
     * String representation of the todo.
     *
     * @return The string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

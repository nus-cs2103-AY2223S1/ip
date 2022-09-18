package functional;

import technical.SaveLine;

/**
 * Class for tasks with no specific time.
 * @author Nicholas Patrick
 */
public class Todo extends Task {
    private static final String TODO_INFOTYPE = "todo";
    /**
     * Construct functional.Task with a fixed name.
     *
     * @param name The name of the task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Construct a task from a SaveLine. If the argument is invalid.
     *
     * @param line The SaveLine containing the necessary information.
     */
    public Todo(SaveLine line) {
        super(line);
    }

    /**
     * Shows the todo name and status as a String.
     *
     * @return A String with the task name and status.
     */
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Turns the task into a SaveLine, so it's ready to be saved. Can also be
     * used to compare two tasks.
     *
     * @return A SaveLine with the data associated with the task.
     */
    @Override
    public SaveLine toData() {
        SaveLine ret = super.toData();
        ret.setInfoType(TODO_INFOTYPE);
        return ret;
    }

    /**
     * Checks whether this is equal to another Object. If the other object is
     * not a Todo, the return value will be false.
     *
     * @param rhs The right hand side of the comparison.
     * @return The boolean stating whether this and the argument are equal.
     */
    @Override
    public boolean equals(Object rhs) {
        if (!(rhs instanceof Todo)) {
            return false;
        }
        Todo rhsDeadline = (Todo) rhs;
        return toData().equals(rhsDeadline.toData());
    }
}

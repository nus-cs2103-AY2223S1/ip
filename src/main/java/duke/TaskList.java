package duke;

/**
 * Contains the task list.
 */
public class TaskList {
    public String list;

    public  TaskList() {}

    /**
     * Sets the list to be current list.
     *
     * @param list A list that is of type String.
     */
    public void set(String list) {
        this.list = list;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    public String get() {
        return this.list;
    }
}

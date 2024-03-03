package duke;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    private String name;
    private String type;
    private String status;
    private char priority;
    private Duke duke = new Duke();

    /**
     * Constructs a todo task.
     *
     * @param name the todo name.
     */
    public Todo(String name) {
        this.name = name;
        this.status = "[ ]";
        this.type = "[T]";
        this.priority = 'L';
    }

    /**
     * Sets the priority of the task.
     * @param priority the priority of the task.
     */
    public void setPriority(char priority) {
        this.priority = priority;
    }

    /**
     * Returns the priority of the task.
     * @return the priority of the task.
     */
    public char getPriority() {
        return this.priority;
    }

    /**
     * Gets the name of the todo task.
     *
     * @return the name of the todo task.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the status of the todo task.
     *
     * @return the status of the todo task.
     */
    @Override
    public String getStatus() {
        return this.status;
    }

    /**
     * Gets the type of the todo task.
     *
     * @return the type of the todo task.
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Sets the status of the task as specified.
     *
     * @param status the status of the task. [X] for done, [ ] for undone.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Prints the description of the todo task that is being added to the task list.
     */
    public void print() {
        System.out.println(Ui.ADD_TASK_HEADER + this.toString()
                + " Now you have " + duke.getCount() + " tasks in the list." + "\n" + Duke.LINE + "\n");
    }

    /**
     * Prints the description of the todo task that is being added to the task list.
     * @return the description of the todo task that is being added to the task list.
     */
    public String printGui() {
        return Ui.ADD_TASK_HEADER_GUI + this.toString()
                + " Now you have " + duke.getCount() + " tasks in the list." + "\n";
    }

    /**
     * Prints the description of the todo task.
     */
    public void list() {
        System.out.println(this.type + this.status + this.priority + this.name);
    }

    /**
     * Prints the description of the todo task.
     * @return the description of the todo task.
     */
    public String listGui() {
        return this.type + this.status + " " + this.priority + " " + this.name;
    }

    /**
     * Returns the string representation the todo task.
     *
     * @return the string representation the todo task.
     */
    public String printDescription() {
        return this.getName();
    }

    /**
     * Returns the full string representation the todo task.
     *
     * @return the full string representation the todo task.
     */
    public String toString() {
        return this.getType() + this.getStatus() + " " + this.getPriority() + " " + this.getName() + " " + "\n";
    }
}

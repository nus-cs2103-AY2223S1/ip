package duke;

/**
 * The Todo class represents a generic Task that the user wishes to complete.
 */

class Todo extends Task {
    private final char tag = 'T';

    /**
     * Constructs an instance of Todo.
     *
     * @param description description of the task.
     */
    public Todo(String description) {
        super(description.substring(5));
    }

    /**
     * Returns the record of the Task's description and completion status.
     *
     * @return the record of the Task.
     */
    @Override
    public String printTask() {
        return String.format("[%s]%s",
                this.tag,
                super.printTask());
    }
}

package caca.tasks;

/**
 * This class represents tasks without any date & time attached to it.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Todo extends Task {

    /**
     * Constructor for creating a Todo.
     *
     * @param description Task description from user input.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for creating a Todo with given isDone status.
     *
     * @param description Task description.
     * @param isDone True if todo is marked as done; false otherwise.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Displays the task type of todo as T.
     *
     * @return T.
     */
    @Override
    public String taskType() {
        return "T";
    }

    /**
     * Formats todo in a file.
     *
     * @return caca.tasks.Todo with task type, status and description.
     */
    @Override
    public String toFileFormat() {
        return String.format("T | %s | %s", this.getStatusIcon(), this.description);
    }

}

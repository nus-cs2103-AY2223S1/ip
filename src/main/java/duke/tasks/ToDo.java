package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a todo item
 */
public class ToDo extends Task {
    private static final String TASK_TYPE = "T";

    /**
     * Constructs a new Todo task
     * @param description The description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a new todo based on the information provided
     * @param todo The todo information
     * @return A todo
     */
    public static ToDo createTodo(String todo) {
        String[] components = todo.split(",");
        ToDo t = new ToDo(components[2]);
        t.setIsDone(components[1].equals("true"));
        t.setDateMarked(components[3]);
        return t;
    }

    /**
     * Returns a string representation of the todo object
     * @return A string representation of the todo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Gets the date of the task
     * @return The date of the task
     */
    @Override
    public LocalDate getDate() {
        return null;
    }

    /**
     * Gets the task type
     * @return The task type
     */
    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Converts the task to the storage format
     * @return The storage format
     */
    @Override
    public String stringify() {
        String sep = System.getProperty("line.separator");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String dateMarkedCompleted = dateMarked == null
                ? "na"
                : dateMarked.format(formatter);
        String storageFormat = String.format("T,%s,%s,%s,%s", isDone, description,
                dateMarkedCompleted, sep);
        return storageFormat;
    }
}

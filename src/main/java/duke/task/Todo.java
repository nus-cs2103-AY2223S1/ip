package duke.task;

import java.time.LocalDate;

/**
 * Represents a todo-type task.
 *
 * @author Derrick Khoo
 */
public class Todo extends Task {

    /**
     * Constructs a todo-type task.
     * @param description the description of the todo-type task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string description of the todo-type task for saving the task
     * to the hard disk.
     *
     * @return the string description
     */
    public String toFileDescription() {
        return "T" + " | " + super.toFileDescription();
    }

    /**
     * Returns an todo-type task from the string description stored in the hard disk.
     *
     * @param input the string description of the todo task
     * @return the todo-type task
     */
    public static Todo fromFileDescription(String input) {
        String[] strArray = input.split(" \\| ", 3);
        boolean isDone;
        Todo todo = new Todo(strArray[2]);
        if (strArray[1].equals("1")) {
            todo.markDone();
        }
        return todo;
    }

    /**
     * Returns a boolean denoting if the event is happening at the queried date.
     *
     * @param localDate the queried date
     * @return false because there is no date specified for a todo-type task
     */
    public boolean isHappeningOnDate(LocalDate localDate) {
        return false;
    }

    /**
     * The string representation of a todo-type task.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

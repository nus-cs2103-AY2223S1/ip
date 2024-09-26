package duke.task;

/**
 * The Todo class represents tasks to be done, but without a specific deadline or time to be done, 
 * and is thus the most basic class of tasks.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo class. 
     * @param description Takes in the description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String literal "T" for the Task type.
     * @return Returns T for Todo tasks.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Overrides the parent class Task's toString() method to include its Task type.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package duke.task;
import java.util.List;

/**
 * Represents a ToDo task with a task description. The ToDo class Inherits Task class.
 */
public class ToDo extends Task {

    /**
     * This method is a ToDo Task constructor.
     * A ToDo Task contains a task description, a task status and tags (all inherited from Task class).
     * @param description a task description string.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * This method is a overloaded ToDo Task constructor.
     * A ToDo Task contains a task description, a task status and tags (all inherited from Task class).
     * @param description a task description string.
     * @param isDone a boolean value of task status (false: not done. true: done).
     * @param tags a list of tags.
     */
    public ToDo(String description, Boolean isDone, List<String> tags) {
        super(description, isDone, tags);
    }

    /**
     * Returns ToDo task status icon. This method overrides the method from its super class Task.
     * In the status icon, [T] represents task type ToDo, [X] represents task status as done,
     * [ ] represents task status as not done.
     * @return String representation of ToDo task status icon.
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "[T][X]" : "[T][ ]"); // mark done task with X
    }

    /**
     * Returns a string format of ToDo task. The format is | Todo | status | description | time | tags
     * This method is used t convert a ToDo task to string and store it in storage.
     * @return string format of ToDo task.
     */
    public String toString() {
        String status = isDone ? "Done  " : "UnDone";
        return String.format("Todo      | %s | %s | %s", status, super.getDescription(), super.printTags());
    }

    /**
     * Returns the task type of ToDo task. This method overrides the method from its super class Task.
     * @return string "todo".
     */
    @Override
    public String taskType() {
        return "todo";
    }

}

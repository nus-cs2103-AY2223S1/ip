package duke.task;

import duke.Task;

/**
 * Represents a Todo task
 */
public class Todo extends Task {
    public Todo(String item) {
        this.setItem(item);
    }

    /**
     * Gets the string representation of the task
     * @return string representation of the task
     */
    public String getTask() {
        return "[T] " + this.getStatusIcon() + " " + this.getItem();
    }

    /**
     * Gets the representation of the task that is stored in the data file
     * @return string representation of the task that is stored in the data file
     */
    public String getFileLine() {
        return "[T]" + "##" + this.getStatusIcon() + "##" + this.getItem();
    }
}

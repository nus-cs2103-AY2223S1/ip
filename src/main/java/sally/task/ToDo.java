package sally.task;

import sally.task.Task;

/**
 * ToDo class to represent new Todo task
 *
 * @author liviamil
 */

public class ToDo extends Task {
    public ToDo(String description, boolean saveTask) {
        super(description, saveTask);
        this.taskType = Type.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package duke.task;

import java.time.LocalDateTime;

/**
 * A Todo_class that encapsulates the information of Todo_task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo_Object.
     *
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo_Object.
     *
     * @param description the description of the task
     * @param status      indicates whether the task has been done
     */
    public Todo(String description, String status) {
        super(description);
        if (status.equals("1")) {
            super.markAsDone();
        }
    }

    @Override
    public String getTaskType() {
        return "Todo";
    }

    @Override
    public LocalDateTime getDate() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

package duke.task;

/**
 * Todo task with TaskType, description and boolean to check whether the task is done yet.
 */
public class Todo extends Task {

    public Todo(TaskType type, String name, boolean isMarked) {
        super(type, name, isMarked);
    }

    /**
     * {@inheritDoc}
     * @return String of todo with details, TaskType, name and isMarked.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

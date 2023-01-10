package duke.task;

/**
 * A task without any date/time attached to it e.g., visit new theme park
 */
public class ToDo extends Task {

    @Override
    public TaskType getTaskType() {
        return TaskType.ToDo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

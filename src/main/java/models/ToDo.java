package models;

/**
 * Encapsulates a task without any date/time attached to it, e.g. visit new theme park
 *
 * @author Emily Ong Hui Qi
 */

public class ToDo extends Task {
    private static final TaskType taskType = TaskType.TODO;

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskTypeIcon() {
        return ToDo.taskType.toString();
    }

    @Override
    public Serializable serialize() {
        String isDone = super.isDone ? "1" : "0";
        String[] data = {ToDo.taskType.toString(), isDone, super.description};
        return new Serializable(data);
    }
}

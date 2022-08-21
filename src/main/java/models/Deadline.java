package models;

/**
 * Encapsulates a task that needs to be done before a specific date/time,
 * e.g., submit report by 11/10/2019 5pm
 *
 * @author Emily Ong Hui Qi
 */

public class Deadline extends Task {
    private static final TaskType taskType = TaskType.DEADLINE;
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeIcon() {
        return Deadline.taskType.toString();
    }

    @Override
    public Serializable serialize() {
        String isDone = super.isDone ? "1" : "0";
        String[] data = {Deadline.taskType.toString(), isDone, super.description, this.deadline};
        return new Serializable(data);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.deadline);
    }
}

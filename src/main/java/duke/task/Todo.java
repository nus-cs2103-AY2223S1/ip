package duke.task;

public class Todo extends Task {

    /**
     * Returns a Todo object.
     *
     * @param taskName Name of the task
     */
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T" + "|" + (this.getTaskStatus() ? "1" : "0") + "|" + this.getTaskName();
    }
}

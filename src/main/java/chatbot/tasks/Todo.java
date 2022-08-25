package chatbot.tasks;

/**
 * The Todo class is a subclass of Task.
 */
public class Todo extends Task {
    public Todo(String taskName) throws IndexOutOfBoundsException {
        super(taskName.substring(5));
    }

    public Todo(String taskName, boolean isComplete) throws IndexOutOfBoundsException {
        super(taskName, isComplete);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String save() {
        return "T | " + this.getStatus() + " | " + this.getTaskName();
    }
}

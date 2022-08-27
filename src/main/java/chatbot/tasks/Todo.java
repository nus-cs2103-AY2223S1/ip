package chatbot.tasks;

/**
 * The Todo class is a subclass of Task.
 */
public class Todo extends Task {
    public static final String TYPE = "todo";

    public Todo(String taskName) {
        super(taskName);
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

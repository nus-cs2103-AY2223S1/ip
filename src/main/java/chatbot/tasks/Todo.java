package chatbot.tasks;

/**
 * The Todo class is a subclass of Task.
 */
public class Todo extends Task {
    public Todo(String taskName, String[] tags) {
        super(taskName, tags);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String save() {
        String tagsString = this.saveTags();
        if (tagsString != null) {
            return String.format("T | %s | %s | %s", this.getStatus(), this.getTaskName(), tagsString);
        }
        return String.format("T | %s | %s", this.getStatus(), this.getTaskName());
    }
}

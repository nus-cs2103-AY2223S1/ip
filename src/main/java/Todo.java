public class Todo extends Task {

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        String taskStatusIndicator = "[T]" + (this.isCompleted() ? "[X] " : "[ ] ");
        return taskStatusIndicator + this.getTaskDescription();
    }
}

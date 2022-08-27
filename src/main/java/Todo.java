public class Todo extends Task {
    public Todo(String input) {
        this.description = input;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.ToDo;
    }

    @Override
    public String toString() {
        String head = "[T]" + "[" + this.getStatusIcon() + "] ";
        return head + this.description;
    }
}

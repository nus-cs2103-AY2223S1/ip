public class Todo extends Task {
    public Todo(String input, boolean isDone) {
        super(isDone);
        this.description = input;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.ToDo;
    }

    @Override
    public String toDataForm() {
        String done = this.isDone ? "1" : "0";
        return "T|" + done + "|" + this.description + "\n";
    }

    @Override
    public String toString() {
        String head = "[T]" + "[" + this.getStatusIcon() + "] ";
        return head + this.description;
    }
}

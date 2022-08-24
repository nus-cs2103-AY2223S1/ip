package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", super.getTaskIcon(), super.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            Todo that = (Todo) o;
            return this.getDescription().equals(that.getDescription());
        }
        return false;
    }
}

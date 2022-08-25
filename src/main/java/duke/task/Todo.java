package duke.task;
public class Todo extends Task {

    public Todo(String task_description) {
        super(task_description);
    }

    public Todo(String task_description, boolean isDone) {
        super(task_description, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

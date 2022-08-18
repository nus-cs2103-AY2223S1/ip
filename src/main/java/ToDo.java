public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }
    @Override
    public String getStatusIcon() {
        return (isDone ? "[T][X]" : "[T][ ]"); // mark done task with X
    }
}

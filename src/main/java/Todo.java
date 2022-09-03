public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.taskType = TaskType.TODO;
    }

    @Override
    public String encode() {
        return super.encode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

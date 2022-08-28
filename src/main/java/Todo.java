public class Todo extends Task{

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", super.getStatusIcon(), super.toString());
    }

    @Override
    public String toFileString() {
        return String.format("T | %s | %s", super.getFileIcon(), super.toString());
    }
}

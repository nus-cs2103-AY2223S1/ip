public class Todo extends Task {

    Todo(String descrition) {
        super(descrition);
    }

    public String getOutput() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

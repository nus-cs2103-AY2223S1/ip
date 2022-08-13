public class ToDo extends Task {
    public ToDo(String task) {
        super(task, "todo");
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

public class ToDoTask extends Task {
    ToDoTask(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
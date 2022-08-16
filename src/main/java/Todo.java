public class Todo extends Task {

    Todo(String task_description) {
        super(task_description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

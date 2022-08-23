public class Todo extends Task {

    Todo(String task_description) {
        super(task_description);
    }

    Todo(String task_description, boolean done) {
        super(task_description, done);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

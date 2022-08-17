public class Todo extends Task {

    public Todo(String taskname) {
        super(taskname);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    public Todo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

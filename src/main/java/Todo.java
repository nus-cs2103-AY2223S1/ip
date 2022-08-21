public class Todo extends Task {

    public Todo (TaskType type, String name) {
        super(type, name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

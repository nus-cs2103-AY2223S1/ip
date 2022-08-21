public class Todo extends Task {

    public Todo (TaskType type, String name, boolean marked) {
        super(type, name, marked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

public class ToDo extends Task {

    public ToDo(String description, TaskType type) {
        super(description, type);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

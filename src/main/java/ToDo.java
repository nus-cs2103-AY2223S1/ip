public class ToDo extends Task {
    public ToDo(String description, boolean saveTask) {
        super(description, saveTask);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

public class ToDo extends Task {
    public ToDo(String description, boolean saveTask) {
        super(description, saveTask);
        this.taskType = Type.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

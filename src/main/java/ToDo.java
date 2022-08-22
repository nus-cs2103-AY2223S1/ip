public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + TaskType.T + "]" + super.toString();
    }

    @Override
    public String toCommand() {
        return TaskType.T + " | " + super.toCommand();
    }
}

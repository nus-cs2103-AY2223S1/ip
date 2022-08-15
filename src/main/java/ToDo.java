public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String TaskInfo() {
        return "[T] [" + getStatusIcon() + "] " + description;
    }
}

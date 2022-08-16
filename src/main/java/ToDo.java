public class ToDo extends Task {

    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }
}

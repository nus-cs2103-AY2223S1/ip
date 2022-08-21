public class ToDoTask extends Task {

    private static final String LABEL = "T";

    protected ToDoTask(String taskTitle) {
        super(taskTitle, TaskType.TODO);
    }

    @Override
    public String toString() {
        return super.getStringRepresentation(LABEL);
    }
}

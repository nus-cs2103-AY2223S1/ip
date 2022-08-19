public class ToDos extends Task {

    private String taskName;
    private boolean isDone = false;

    public ToDos(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

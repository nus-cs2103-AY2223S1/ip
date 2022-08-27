public class ToDos extends Task {

    private String taskName;

    public ToDos(String taskName) {
        super(taskName);
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String getSaveData() {
        return "T" + " | " + super.isDone() + " | " + taskName;
    }
}

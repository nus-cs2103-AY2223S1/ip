public class TodoTask extends Task {
    TodoTask(String taskname) {
        super(taskname);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

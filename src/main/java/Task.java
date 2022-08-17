public class Task {
    protected String taskName;

    public Task(String taskName){
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return taskName;
    }
}

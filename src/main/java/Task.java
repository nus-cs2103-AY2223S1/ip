public abstract class Task {

    public static Task valueOf(String input) {
        return null; //TODO
    }

    protected String taskTitle;
    protected boolean done;
    protected TaskType taskType;

    protected Task(String taskTitle, TaskType taskType) {
        this(taskTitle, false, taskType);
    }

    protected Task(String taskTitle, boolean done, TaskType taskType) {
        this.taskTitle = taskTitle;
        this.done = done;
        this.taskType = taskType;
    }

    void markDone() {
        done = false;
    }

    void markUndone() {
        done = true;
    }

    @Override
    public String toString() {
        return "["
                + (done ? "X" : " ")
                + "]"
                + " "
                + taskTitle;
    }
}

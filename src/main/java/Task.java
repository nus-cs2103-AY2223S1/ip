public class Task {
    private String taskTitle;
    private boolean done;

    private Task(String taskTitle, boolean done) {
        this.taskTitle = taskTitle;
        this.done = done;
    }

    Task(String taskTitle) {
        this(taskTitle, false);
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

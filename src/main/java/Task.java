public class Task {
    private boolean done;
    private String taskName;

    public Task (String taskName, boolean done) {
        this.taskName = taskName;
        this.done = done;
    }

    public String getTask() {
        return taskName;
    }

    public String isDoneString() {
        if (done) {
            return "[X]";
        }
        return "[ ]";
    }

    public void markDone() {
        done = true;
    }



}

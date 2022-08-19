enum TaskType {
    TODO, DEADLINE, EVENT;
}

public class Task {
    private boolean done;
    private String taskName;
    private TaskType taskType;
    private String time;

    public Task (String taskName, String taskType, boolean done) {
        this.done = done;
        if (taskType.equals("TODO")) {
            this.taskType = TaskType.TODO;
            this.taskName = taskName;
            this.time = "";
        } else if (taskType.equals("DEADLINE")) {
            this.taskType = TaskType.DEADLINE;
            this.taskName = taskName.substring(0, taskName.indexOf("/") - 1);
            this.time = "(" + taskName.substring(taskName.indexOf("/") + 1) + ")";
        } else if (taskType.equals("EVENT")) {
            this.taskType = TaskType.EVENT;
            this.taskName = taskName.substring(0, taskName.indexOf("/") - 1);
            this.time = "(" + taskName.substring(taskName.indexOf("/") + 1) + ")";
        }
    }

    public String getTask() {
        return this.taskName;
    }

    public String isDoneString() {
        if (done) {
            return "[X]";
        }
        return "[ ]";
    }

    @Override
    public String toString() {
        if (done) {
            return "[" + taskType.toString().charAt(0) + "][X] " + this.taskName + " " + this.time;
        }
        return "[" + taskType.toString().charAt(0) + "][ ] " + this.taskName + " " + this.time;
    }

    public void markDone() {
        done = true;
    }

    public void unmarkDone() {
        done = false;
    }

}

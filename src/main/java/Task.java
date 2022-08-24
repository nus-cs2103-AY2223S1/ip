import java.time.LocalDate;

enum TaskType {
    TODO, DEADLINE, EVENT;
}

public class Task {
    private boolean done = false;
    private String taskName;
    private TaskType taskType;
    private LocalDate time;

    public Task (String taskName, String taskType, String time) {
        if (taskType.equals("TODO")) {
            this.taskType = TaskType.TODO;
            this.taskName = taskName;
            this.time = null;
        } else if (taskType.equals("DEADLINE")) {
            this.taskType = TaskType.DEADLINE;
            this.taskName = taskName.substring(0, taskName.indexOf("/") - 1);
            // this.time = "(" + taskName.substring(taskName.indexOf("/") + 1) + ")";
            this.time = LocalDate.parse(time);
        } else if (taskType.equals("EVENT")) {
            this.taskType = TaskType.EVENT;
            this.taskName = taskName.substring(0, taskName.indexOf("/") - 1);
            // this.time = "(" + taskName.substring(taskName.indexOf("/") + 1) + ")";
            this.time = LocalDate.parse(time);
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

    public String toTxt() {
        if (taskType == TaskType.TODO) {
            if (done) {
                return taskType.toString().charAt(0) + " | 1 | " + this.taskName + "\n";
            }
            return taskType.toString().charAt(0) + " | 0 | " + this.taskName + "\n";
        }
        if (done) {
            // return taskType.toString().charAt(0) + " | 1 | " + this.taskName + " | " + this.time.substring(1, this.time.length() - 1) + "\n";
            return taskType.toString().charAt(0) + " | 1 | " + this.taskName + " | " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy") + "\n";
        }
        return taskType.toString().charAt(0) + " | 0 | " + this.taskName + " | " + this.time.substring(1, this.time.length() - 1) + "\n";
    }

    public void markDone() {
        done = true;
    }

    public void unmarkDone() {
        done = false;
    }

}

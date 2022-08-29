package duke.task;

public abstract class Task {
    private final String taskName;
    private boolean taskDone;

    Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
    }

    public boolean check() {
        if (taskDone) {
            return false;
        } else {
            taskDone = true;
            return true;
        }
    }

    public boolean uncheck() {
        if (taskDone) {
            taskDone = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", (taskDone ? 'X' : ' '), taskName);
    }

    public String saveFileFormat() {
        return String.format("%d###%s", taskDone ? 1 : 0, taskName);
    }
}

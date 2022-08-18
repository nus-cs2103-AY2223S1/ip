public class Task {
    private final String taskName;
    private boolean taskDone;

    Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
    }

    boolean check() {
        if (taskDone) {
            return false;
        } else {
            taskDone = true;
            return true;
        }
    }

    boolean uncheck() {
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
}

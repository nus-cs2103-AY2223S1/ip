package duke.task;

public abstract class Task {
    private final String taskName;
    private boolean isCompleted;

    public Task(String name) {
        taskName = name;
        isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void markComplete() {
        isCompleted = true;
    }

    public void markIncomplete() {
        isCompleted = false;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

}
public abstract class Task {
    private String taskName;
    private boolean completed;
    private static int taskCounter = 0;

    public Task(String name) {
        this.taskName = name;
        this.completed = false;
        taskCounter++;
    }

    public void complete() {
        this.completed = true;
    }

    public void incomplete() {
        this.completed = false;
    }

    public static int getCount() {
        return taskCounter;
    }

    public static void reduceCount() {
        taskCounter--;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public abstract String getTaskType();

    public abstract String getTime();

    @Override
    public String toString() {
        return (this.completed ? "[X] " : "[ ] ") + this.taskName;
    }
}

package duke.task;

public class Task {
    private TaskType taskType;
    private String name;
    private boolean isMarked;

    public Task(TaskType taskType, String name, boolean isMarked) {
        this.taskType = taskType;
        this.name = name;
        this.isMarked = isMarked;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public String getName() {
        return name;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked() {
        isMarked = true;
    }

    public void setUnmarked() {
        isMarked = false;
    }

    @Override
    public String toString() {
        if (isMarked) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}


package duke.task;

public class Task {
    private TaskType type;
    private String name;
    private boolean isMarked;

    public Task(TaskType type, String name, boolean isMarked) {
        this.type = type;
        this.name = name;
        this.isMarked = isMarked;
    }

    public TaskType getTaskType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void markT() {
        isMarked = true;
    }

    public void unmarkT() {
        isMarked = false;
    }

    @Override
    public String toString() {
        if (isMarked) {
            return "[X] " + name;
        }

        return "[ ] " + name;
    }
}


public class Task {
    private TaskType type;
    private String name;
    private boolean marked;

    public Task(TaskType type, String name, boolean marked) {
        this.type = type;
        this.name = name;
        this.marked = marked;
    }

    public TaskType getTaskType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isMarked() {
        return marked;
    }

    public void markT() {
        marked = true;
    }

    public void unmarkT() {
        marked = false;
    }

    @Override
    public String toString() {
        if (marked) {
            return "[X] " + name;
        }

        return "[ ] " + name;
    }
}


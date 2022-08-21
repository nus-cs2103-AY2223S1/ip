public class Task {
    private String name;
    private TaskType type;
    private boolean marked = false;

    public Task(TaskType type, String name) {
        this.type = type;
        this.name = name;
    }

    public void getName() {
        System.out.println(name);
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


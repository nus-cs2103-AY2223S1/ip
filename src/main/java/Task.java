public class Task {
    private String name;
    private boolean completed;

    public Task (String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public Task (String name) {
        this(name, false);
    }

    public void markComplete() {
        this.completed = true;
    }

    public void markIncomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", name);
    }
}

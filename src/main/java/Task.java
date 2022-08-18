public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", name);
    }
}

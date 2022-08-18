public class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}

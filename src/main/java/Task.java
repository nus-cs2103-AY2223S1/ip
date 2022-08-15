public class Task {
    private final String TASK;
    Task(String task) {
        this.TASK = task;
    }

    @Override
    public String toString() {
        return this.TASK;
    }
}

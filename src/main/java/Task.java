public class Task {
    protected String description;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}

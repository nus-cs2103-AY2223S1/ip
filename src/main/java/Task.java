class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s", isDone ? "✔" : " ", this.description);
    }
}

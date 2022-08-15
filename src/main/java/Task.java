class Task {
    private final String description;
    private boolean completed;

    Task(String description) {
        this.description = description;
        this.completed = false;
    }

    void mark() {
        completed = true;
    }

    void unmark() {
        completed = false;
    }

    boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (completed) ? "X" : " ", description);
    }
}

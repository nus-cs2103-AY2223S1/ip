class Task {
    private final String description;
    private boolean completed;

    Task(String description) {
        this.description = description;
        this.completed = false;
    }

    void mark() {
        this.completed = true;
    }

    void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return String.format("[ ] %s", (completed) ? "X" : " ", description);
    }
}

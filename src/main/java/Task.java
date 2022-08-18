class Task {
    String description;

    public Task(String text) {
        description = text;
    }

    @Override
    public String toString() {
        return this.description;
    }
}

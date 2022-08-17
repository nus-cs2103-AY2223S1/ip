class Task {
    private final String taskDescription;
    private boolean isDone;

    Task(String description) {
        this.taskDescription = description;
        this.isDone = false;
    }

    void markAsDone() {
        this.isDone = true;
    }

    void unmarked() {
        this.isDone = false;
    }

    String getStatus() {
        if (this.isDone == true) {
            return "X";
        } else {
            return " ";
        }
    }

    String getTaskDescription() {
        return this.taskDescription;
    }

    public String toString() {
        return "[" + this.getStatus() + "] " + this.getTaskDescription();
    }
}
package duke;
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

    void markAsUndone() {
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

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.getTaskDescription();
    }

    public String write() {
        return ":" + this.getStatus() + ":" + this.getTaskDescription();
    }

}
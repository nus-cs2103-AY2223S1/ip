public class Task {
    private boolean isMarked = false;
    private String description = "";

    public Task(String description) {
        this.description = description;
    }

    public String markTask() {
        this.isMarked = true;
        return "Nice! I've marked this task as done:\n " + this.toString();
    }

    public String unmarkTask() {
        this.isMarked = false;
        return "OK, I've marked this task as not done yet:\n " + this.toString();
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return String.format("[%c] %s", isMarked ? 'X' : ' ', description);
=======
        return String.format("[%s][%s] %s", this.taskTypeString(),isMarked ? "X" : " ", description);
    }

    /**
     * Encodes the task for storage.
     * Format of the event is TASK_TYPE|IS_MARKED|DESCRIPTION.
     * @return String encoding of the task.
     */
    public String encodeForStorage() {
        return String.format("%s|%s|%s", this.taskTypeString(), this.isMarked ? "Y" : "N", this.description);
    }

    private String taskTypeString() {
        switch (this.type) {
        case TODO:
            return "T";
        case EVENT:
            return "E";
        case DEADLINE:
            return "D";
        default:
            return "";
        }
>>>>>>> 038da11 (Refactor using OOP)
    }
}

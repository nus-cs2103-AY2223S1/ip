public abstract class Task {
    public static String SAVE_SEPARATOR = "%#%";

    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "x" : " ") + "] " + this.description;
    }

    public boolean changeStatus(Duke.TaskStatus status) {
        if (status == Duke.TaskStatus.MARK) {
            return this.markDone();
        }
        return this.markUndone();
    }

    public String getIsDoneString() {
        return this.isDone ? "1" : "0";
    }

    public String getDescription() {
        return this.description;
    }

    private boolean markDone() {
        boolean temp = this.isDone;
        this.isDone = true;
        return !temp;
    }

    private boolean markUndone() {
        boolean temp = this.isDone;
        this.isDone = false;
        return temp;
    }

    public abstract String toSave();
}

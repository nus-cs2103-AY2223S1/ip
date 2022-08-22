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

    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getIsDoneString() {
        return this.isDone ? "1" : "0";
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String toSave();
}

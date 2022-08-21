public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatus() {
        return this.isDone 
            ? "X"
            : "";
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String icon = this.getStatus();
        return String.format("[%s] %s", icon, this.description);
    }

    public abstract String SaveString();
}

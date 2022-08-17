public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

//    public String getDescription() {
//        return this.description;
//    }
//
//    public boolean isDone() {
//        return this.isDone;
//    }

    public String getStatusIcon() {
        return this.isDone ? "x" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

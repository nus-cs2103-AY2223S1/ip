class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void doTask() {
        isDone = true;
    }

    public void undoTask() {
        isDone = false;
    }

    public String getTask() {
        return this.description;
    }

    public String getState() {
        return isDone ? "1" : "0";
    }

    public String getTime() {
        return (this instanceof Deadline) ? ((Deadline) this).getBy() : ((Event) this).getBy();
    }

    public String getType() {
        if (this instanceof Todo) {
            return "T";
        } else if (this instanceof Deadline) {
            return "D";
        } else {
            return "E";
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }
}
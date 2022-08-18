package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void toggleStatus() {
        this.isDone = !isDone;
    }

    public boolean isDone() {
        return this.isDone;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task x = (Task) obj;
            if (this.description == null || x.description == null) {
                return false;
            }
            return this.description.equals(x.description);
        }

        return false;
    }
}

package tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int total = 0;

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.total += 1;
    }

    public String getStatusIcon() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public boolean isDone() {
        return this.isDone;
    }


    public String getDescription() {
        return this.description;
    }

    public int getTotal() {
        return this.total;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        String result = "[" + getStatusIcon() + "] ";
        return result;
    }

    public String fileString() {
        String write = (isDone ? "1 / " : "0 / ");
        return write;
    }
}

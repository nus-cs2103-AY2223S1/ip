package tasks;

public class Todos extends Task {
    protected String description;
    protected boolean isDone;

    public Todos(String description) {
        super(description);
        this.description = description;
        this.isDone = false;
    }

    public Todos(String description, boolean isDone) {
        super(description);
        this.description = description;
        this.isDone = isDone;
    }



    public String toString() {
        String result = "[T]" + "[" + getStatusIcon() + "] " + this.description;
        return result;
    }

    public String fileString() {
        String write = "T / " + (isDone ? "1 / " : "0 / ") + this.description.strip();
        return write;
    }

}

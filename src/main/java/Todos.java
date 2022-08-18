public class Todos extends Task {
    protected String description;
    protected boolean isDone;

    public Todos(String description) {
        super(description);
        this.description = description;
        this.isDone = false;
    }


    public String toString() {
        String result = "[T]" + "[" + getStatusIcon() + "] " + this.description;
        return result;
    }
}

public class Task {
    protected String description;
    protected   boolean isDone;

    Task (String description) {
        this.description = description;
        this.isDone = false;
    }
    Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        char mark;
        if (this.isDone) {
            mark = 'X';
        } else {
            mark = ' ';
        }
        return ("[" + mark + "] " + this.getDescription());
    }
}
public class Task {
    protected String type;
    protected String description;
    protected String dateTime;
    protected   boolean isDone;

    Task (String description) {
        this.description = description;
        this.isDone = false;
    }
    Task (String type, String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
        this.isDone = false;
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
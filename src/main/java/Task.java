public class Task {
    protected String type;
    protected String description;
    protected String dateTime;
    protected boolean isDone;

    Task (String type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    Task (String type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    Task (String type, String description, String dateTime) {
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
        this.isDone = false;
    }

    Task (String type, String description, String dateTime, boolean isDone) {
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
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

    public String log() {
        int binIsDone;
        if (this.isDone) {
            binIsDone = 1;
        } else {
            binIsDone = 0;
        }
        switch(type) {
        case "todo":
            return String.format("%d,todo %s\n", binIsDone, this.description);
        case "deadline":
            return String.format("%d,deadline %s/by%s\n", binIsDone, this.description, this.dateTime);
        case "event":
            return String.format("%d,event %s/at%s\n", binIsDone, this.description, this.dateTime);
        default:
            return "N";
        }
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
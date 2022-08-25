public class Task {
    public static final String ICON_DONE = "X";
    public static final String ICON_UNDONE = " ";

    public String details;
    public boolean isDone;

    public Task(String details) {
        this.details = details;
        this.isDone = false;
    }

    public static Task fromEncoded(String s) {
        String[] pieces = s.split("\\|");
        Task task;
        switch (pieces[0]) {
        case "T":
            task = new Todo(pieces[2]);
            break;
        case "D":
            task = new Deadline(pieces[2], pieces[3]);
            break;
        case "E":
            task = new Event(pieces[2], pieces[3]);
            break;
        default:
            task = new Task(pieces[2]);
            break;
        }
        if (pieces[1].equals(ICON_DONE)) {
            task.isDone = true;
        }

        return task;
    }

    public String getStatusIcon() {
        return isDone ? ICON_DONE : ICON_UNDONE;
    }

    public String getTaskIcon() {
        return " ";
    }

    public String getDetails() {
        return details;
    }

    public String getEncodedDetails() {
        return details;
    }

    public String getEncoded() {
        return getTaskIcon() + "|" + getStatusIcon() + "|" + getEncodedDetails();
    }

    @Override
    public String toString() {
        return "[" + getTaskIcon() + "][" + getStatusIcon() + "] " + getDetails();
    }
}

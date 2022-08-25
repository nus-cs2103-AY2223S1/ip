public class Task {
    public String details;
    public boolean isDone;

    public Task(String details) {
        this.details = details;
        this.isDone = false;
    }

    public static Task fromEncoded(String s) {
        String[] pieces = s.split("\\|");
        switch (pieces[0]) {
        case "T":
            return new Todo(pieces[1]);
        case "D":
            return new Deadline(pieces[1], pieces[2]);
        case "E":
            return new Event(pieces[1], pieces[2]);
        default:
            return new Task(pieces[1]);
        }
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
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

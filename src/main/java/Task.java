public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public abstract String encodeToString();
    
    public static Task decodeFromString(String line) {
        String[] strings = line.split("\\|");
        switch (strings[0]) {
        case "T":
            return new Todo(strings[2], strings[1].equals("X"));
        case "D":
            return new Deadline(strings[2], strings[3], strings[1].equals("X"));
        case "E":
            return new Event(strings[2], strings[3], strings[1].equals("X"));
        default:
            return null;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

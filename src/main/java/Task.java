public abstract class Task {
    protected String name;
    protected boolean done;
    protected String type;
    protected String date;

    protected Task(String name, String date) {
        this.name = name;
        this.done = false;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + getStatus() + " " + stringType() + " " + name + " " + date;
    }

    public static Task createTask(String type, String name, String date) {
        switch (type) {
            case "T":
                return new ToDo(name, "");
            case "D":
                return new Deadline(name, date);
            default:
                return new Event(name, date);
        }
    }

    public void markDone() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public String getStatus() {
        return done ? "[✓]" : "[ ]";
    }

    protected String getType() {
        return type;
    }

    protected boolean isDone() {
        return done;
    }

    protected String getName() {
        return name;
    }

    protected String getDate() {
        return date;
    }

    protected abstract String stringType();
}

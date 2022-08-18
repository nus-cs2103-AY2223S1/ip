public class Task {
    protected String name;
    protected boolean done;
    Task(String name) {
        this.name = name;
        this.done = false;
    }

    @Override
    public String toString() {
        return name;
    }

    public void markDone() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public String getStatus() {
        if (done) return "[✓] ";
        else return "[ ] ";
    }

}

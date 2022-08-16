public class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void completed() {
        this.done = true;
    }

    public void uncompleted() {
        this.done = false;
    }

    public String getStatusIcon() {
        return (this.done) ? "X" : " ";
    }

    @Override
    public String toString() {
        return this.name;
    }
}

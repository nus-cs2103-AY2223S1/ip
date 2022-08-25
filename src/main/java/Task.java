public abstract class Task {
    private String description;
    private boolean marked;

    public Task(String description) {
        this.description = description;
        this.marked = false;
    }

    public abstract String getType();
    public abstract String getDate();

    public String getTask() {
        return description;
    }

    public boolean getMarked() {
        return this.marked;
    }

    public void setMarked(boolean b) {
        this.marked = b;
    }

    @Override
    public String toString() {
        return "[" + (marked ? "X" : " ") + "] " + description;
    }
}

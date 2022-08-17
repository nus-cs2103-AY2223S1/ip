public class Task {
    private String description;
    private boolean marked;

    public Task(String description) {
        this.description = description;
        this.marked = false;
    }

    public void setMarked(boolean b) {
        this.marked = b;
    }

    @Override
    public String toString() {
        return "[" + (marked ? "X" : " ") + "] " + description;
    }
}

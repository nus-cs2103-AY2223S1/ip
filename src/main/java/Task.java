public class Task {
    private boolean isDone;
    private String text;

    public Task(boolean isDone, String text) {
        this.isDone = isDone;
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', this.text);
    }
}

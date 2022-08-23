package yilia.task;

public class Task {
    public boolean isDone = false;
    private final String content;
    public Task(String content) {
        this.content = content;
    }
    public Task(String content, boolean isDone) {
        this(content);
        this.isDone = isDone;
    }
    public void check() {
        isDone = true;
    }
    public void uncheck() {
        isDone = false;
    }
    @Override
    public String toString() {
        return "[" + (isDone? 'X': ' ') + "] " + content;
    }
    public String parse() {
        return content;
    }
}

package yilia.task;
public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }
    public Todo(String content, boolean isDone) {
        super(content, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String parse() {
        return "T / " + (status() ? "1" : "0") + " / " + super.parse();
    }
}

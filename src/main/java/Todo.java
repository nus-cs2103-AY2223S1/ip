public class Todo extends Task{
    public Todo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return this.isDone() ? "[T][X] " + this.getContent() : "[T][ ] " + this.getContent();
    }
}

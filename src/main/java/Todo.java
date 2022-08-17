public class Todo extends Task {
    public Todo(boolean isDone, String text) {
        super(isDone, text);
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

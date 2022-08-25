public class Todo extends Task {
    public Todo(boolean isDone, String text, boolean isPrinting) {
        super(isDone, text, isPrinting);
        if (isPrinting) {
            System.out.println(this.toString());
        }
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

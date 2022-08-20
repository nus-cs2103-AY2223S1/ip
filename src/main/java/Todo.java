public class Todo extends Task {
    Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("📜 %s", super.toString());
    }
}

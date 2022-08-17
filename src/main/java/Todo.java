public class Todo extends Task {
    public Todo(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return "(T) " + super.toString();
    }
}

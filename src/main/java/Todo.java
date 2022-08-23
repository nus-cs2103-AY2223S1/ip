public class Todo extends Task {
    private Todo(String description) {
        super(description);
    }

    public static Todo fromUserInput(String userInput) {
        return new Todo(userInput);
    }

    @Override
    public String toString() {
        return "(T) " + super.toString();
    }
}

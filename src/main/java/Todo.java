public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public static Todo createTodo(String input) {
        return new Todo(input);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

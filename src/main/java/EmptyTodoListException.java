public class EmptyTodoListException extends Exception {
    public EmptyTodoListException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}

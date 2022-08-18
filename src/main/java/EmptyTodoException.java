public class EmptyTodoException extends DukeException {
    public EmptyTodoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}

public class EmptyTodoException extends DukeException{

    public EmptyTodoException() {
        super("The description of a todo cannot be empty");
    }

}

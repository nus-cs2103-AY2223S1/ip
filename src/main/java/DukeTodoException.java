public class DukeTodoException extends DukeException{

    public DukeTodoException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "  ---- \n  :( The description of a todo cannot be empty!\n  ----";
    }
}

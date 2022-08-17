public class TodoException extends DukeException{
    public TodoException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        String message = Duke.line + "\n" + "☹ OOPS!!! The description of a todo cannot be empty." +
                "\n" + Duke.line;
        return message;
    }
}

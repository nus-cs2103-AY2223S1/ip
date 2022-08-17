public class DukeException extends Exception{
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        String message = Duke.line + "\n" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +
                "\n" + Duke.line;
        return message;
    }
}

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String command) {
        super(String.format(Duke.line + "\n" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +  "\n" + Duke.line));
    }
}

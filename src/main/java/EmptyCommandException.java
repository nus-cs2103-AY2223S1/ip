public class EmptyCommandException extends Exception {
    public EmptyCommandException(String command) {
        super(String.format(Duke.line + "\n" + "" +
                "☹ OOPS!!! The description of a "+ command +" cannot be empty." + "\n" + Duke.line));
    }
}

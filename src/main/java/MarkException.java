public class MarkException extends Exception {
    public MarkException(String command) {
        super(String.format(Duke.line + "\n" + "" +
                "☹ OOPS!!! Which tasks would you like to " + command + "\n" + Duke.line));
    }
}

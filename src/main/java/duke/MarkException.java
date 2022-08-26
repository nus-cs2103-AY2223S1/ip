package duke;
public class MarkException extends Exception {
    public MarkException(String command) {
        super(String.format(Duke.LINE + "\n" + ""
                + "☹ OOPS!!! Which tasks would you like to " + command + "\n" + Duke.LINE));
    }
}

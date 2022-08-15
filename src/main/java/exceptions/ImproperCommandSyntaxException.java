package exceptions;

public class ImproperCommandSyntaxException extends RuntimeException {

    public static String ERROR_MESSAGE =
        "____________________________________________________________" +
        "\n ☹ ERROR! DEADLINE AND EVENT TASKS MUST CONTAIN A \"/\".\n" +
        "____________________________________________________________";

    public ImproperCommandSyntaxException() {
        super(ERROR_MESSAGE);
    }

    public ImproperCommandSyntaxException(String message) {
        super(message);
    }
}

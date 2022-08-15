package exceptions;

public class ImproperCommandSyntaxException extends RuntimeException {

    private final int errorType;

    public static String ERROR_MESSAGE =
        "____________________________________________________________" +
        "\n ☹ ERROR! TASK IS IN THE WRONG FORMAT!\n" +
        "____________________________________________________________";

    public static String ERROR_MESSAGE_NO_BY =
        "____________________________________________________________" +
        "\n ☹ ERROR! DEADLINE TASKS MUST CONTAIN \"BY\"!\n" +
        "____________________________________________________________";

    public static String ERROR_MESSAGE_NO_AT =
        "____________________________________________________________" +
        "\n ☹ ERROR! EVENT TASKS MUST CONTAIN \"AT\"!\n" +
        "____________________________________________________________";

    public ImproperCommandSyntaxException() {
        this(0);
    }

    public ImproperCommandSyntaxException(int errorType) {
        this(errorType, ERROR_MESSAGE);
    }

    public ImproperCommandSyntaxException(int errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public int getErrorType() {
        return errorType;
    }
}

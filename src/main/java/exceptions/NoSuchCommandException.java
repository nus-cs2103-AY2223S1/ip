package exceptions;

public class NoSuchCommandException extends HenryException {

    public static String ERROR_MESSAGE =
        "____________________________________________________________" +
        "\n ☹ ERROR! THE COMMAND YOU ENTERED IS NOT RECOGNIZED.\n" +
        "____________________________________________________________";

    public NoSuchCommandException() {
        super(ERROR_MESSAGE);
    }

    public NoSuchCommandException(String message) {
        super(message);
    }
}

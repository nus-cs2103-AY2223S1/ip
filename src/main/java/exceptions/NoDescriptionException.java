package exceptions;

public class NoDescriptionException extends HenryException {

    public static String ERROR_MESSAGE =
        "____________________________________________________________" +
        "\n ☹ ERROR! THE DESCRIPTION OF A TO-DO CANNOT BE EMPTY.\n" +
        "____________________________________________________________";

    public NoDescriptionException() {
        super(ERROR_MESSAGE);
    }

    public NoDescriptionException(String message) {
        super(message);
    }
}

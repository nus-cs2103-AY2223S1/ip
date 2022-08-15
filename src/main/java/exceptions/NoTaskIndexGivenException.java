package exceptions;

public class NoTaskIndexGivenException extends HenryException {

    public static String ERROR_MESSAGE =
        "____________________________________________________________" +
        "\n ☹ ERROR! THE INDEX OF A TASK MUST BE GIVEN.\n" +
        "____________________________________________________________";

    public NoTaskIndexGivenException() {
        super(ERROR_MESSAGE);
    }

    public NoTaskIndexGivenException(String message) {
        super(message);
    }
}

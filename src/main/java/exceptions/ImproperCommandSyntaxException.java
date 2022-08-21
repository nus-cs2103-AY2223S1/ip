package exceptions;

public class ImproperCommandSyntaxException extends RuntimeException {

    public static String ERROR_MESSAGE =
        "____________________________________________________________"
        + "\n ☹ ERROR! TASK IS IN THE WRONG FORMAT!\n"
        + "____________________________________________________________";

    public ImproperCommandSyntaxException()  {
        super(ERROR_MESSAGE);
    }
}

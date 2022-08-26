public class DukeException extends Exception{

    DukeException(String error) {
        super(error);
    }


    public static String taskErrorMessage(String command) {
        String modifiedComm = command.replace(" ", "");
        String errorText = String.format("OOPS!!! The description of a %s cannot be empty.", modifiedComm);
        return errorText;
    }

    public static String taskErrorMessage() {
        String errorText = String.format("OOPS!!! I'm sorry, but I don't know what that means :-(");
        return errorText;
    }

    public static void DateTimeFormatErrorMessage() {
        String errorText = String.format("OOPS!!! Please input deadline in this format yyyy-MM-dd\n" +
                "E.g: 2020-01-01");
        Duke.echo(errorText);
    }
}

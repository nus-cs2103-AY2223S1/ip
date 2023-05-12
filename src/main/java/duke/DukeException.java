package duke;


/**
 * Class that deals with exceptions
 * @author Ashiqur Rahman A0230107Y
 */
public class DukeException extends Exception{

    DukeException(String error) {
        super(error);
    }

    /**
     * Method that reminds user that description of Task cannot be empty
     * @param command User input
     * @return String errorText
     */
    public static String taskErrorMessage(String command) {
        String modifiedComm = command.replace(" ", "");
        String errorText = String.format("OOPS!!! The description of a %s cannot be empty.", modifiedComm);
        return errorText;
    }

    /**
     * Method that reminds user that description of Task cannot be empty
     * @return String errorText
     */
    public static String taskErrorMessage() {
        String errorText = String.format("OOPS!!! I'm sorry, but I don't know what that means :-(");
        return errorText;
    }

    /**
     * Method that reminds user that taskList index is greater than taskList size
     * @param tasklist Arraylist of tasks
     * @return String errorText
     */
    public static String IndexOutofBoundsException(TaskList tasklist) {
        String errorText = String.format("OOPS!!! There is only %d task(s) in the tasklist", tasklist.size());
        return errorText;
    }

    /**
     * Method that reminds user to use proper date format for deadline object
     */
    public static String DateTimeFormatErrorMessage() {
        String errorText = String.format("OOPS!!! Please input deadline in this format yyyy-MM-dd\n" +
                "E.g: 2020-01-01");
        return Parser.echo(errorText);
    }

    /**
     * Method that shows user the full proper format for deadline command
     */
    public static String DeadlineFormatErrorMessage() {
        String errorText = String.format("OOPS!!! Please input deadline in this format\n" +
                "E.g: deadline Project Submission /by 2021-07-24");
        return Parser.echo(errorText);
    }

    /**
     * Method that shows user the full proper format for event command
     */
    public static String EventErrorMessage() {
        String errorText = String.format("OOPS!!! Please input event in this format\n" +
                "E.g: event Party /at my place 3pm");
        return Parser.echo(errorText);
    }

    /**
     * Method that shows that index is missing from command
     */
    public static String IndexMissingMessage() {
        String errorText = Parser.echo("OOPS!!! Please provide an index!");
        return errorText;
    }
}

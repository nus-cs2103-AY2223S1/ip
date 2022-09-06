package duke.support;

/**
 * The DukeException class holds methods that will handle
 * checked exceptions that may arise in the Duke class
 * @author lauralee
 */
public class DukeException extends Exception {

    /**
     * todo Exception handler
     */
    public static String todoException() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }

    /**
     * task Exception handler
     *
     * @return
     */
    public static String taskException() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * date time Exception Handler
     */
    public static String dateTimeException() {
        return "☹ OOPS!!! Please provide a valid date in yyyy-mm-dd format";
    }

}

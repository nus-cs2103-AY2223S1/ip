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
    public static void todoException() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * task Exception handler
     */
    public static void taskException() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * date time Exception Handler
     */
    public static void dateTimeException() {
        System.out.println("☹ OOPS!!! Please provide a date in yyyy-mm-dd format");
    }
}

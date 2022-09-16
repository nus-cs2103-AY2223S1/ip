package duke.support;

/**
 * The DukeException class holds methods that will handle
 * checked exceptions that may arise in the Duke class.
 *
 * @author lauralee
 */
public class DukeException extends Exception {

    /**
     * Todo exception handler.
     *
     * @return The description shown when a Todo task exception is detected.
     */
    public static String todoException() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }

    /**
     * Task exception handler.
     *
     * @return The description shown when a task exception is detected.
     */
    public static String taskException() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Date time exception Handler.
     *
     * @return The description shown when a date time exception is detected.
     */
    public static String dateTimeException() {
        return "☹ OOPS!!! Please provide a valid date in yyyy-mm-dd format";
    }

    /**
     * Find exception handler.
     *
     * @return The description shown when a find exception is detected.
     */
    public static String findException() {
        return "☹ OOPS!!! This word cannot be found in any of the tasks in your task list.";
    }

    /**
     * Snooze exception handler.
     *
     * @return The description shown when a snooze exception is detected.
     */
    public static String snoozeException() {
        return "☹ OOPS!!! This task cannot be snoozed.";
    }
}

package duke.support;

/**
 * The DukeException class holds exception subclasses that will handle
 * checked exceptions that may arise in the Duke class.
 *
 * @author lauralee
 */
public class DukeException extends Exception {

    /**
     * Constructs a Duke exception.
     *
     * @param message shown when the respective task is thrown.
     */
    public DukeException(String message) {
        super(message);
    };

    /**
     * Todo exception handler class.
     *
     * @author lauralee
     */
    public static class TodoException extends DukeException {

        /**
         * Constructs a ToDo exception.
         */
        public TodoException() {
            super("OOPS!!! The description of a todo cannot be empty.");
        }

    }

    /**
     * Task exception handler class.
     *
     * @author lauralee
     */
    public static class TaskException extends DukeException {

        /**
         * Constructs a Task exception.
         */
        public TaskException() {
            super("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Date time exception Handler.
     *
     * @author lauralee
     */
    public static class DateTimeException extends DukeException {

        /**
         * Constructs a Date Time exception.
         */
        public DateTimeException() {
            super("OOPS!!! Please provide a valid date in yyyy-mm-dd format");
        }
    }

    /**
     * Find exception handler class.
     *
     * @author lauralee
     */
    public static class FindException extends DukeException {
        /**
         * Constructs a Find exception.
         */
        public FindException() {
            super("OOPS!!! This word cannot be found in any of the tasks in your task list.");
        }
    }

    /**
     * Snooze exception handler class.
     *
     * @author lauralee
     */
    public static class SnoozeException extends DukeException {
        /**
         * Constructs a Snooze exception.
         */
        public SnoozeException() {
            super("OOPS!!! This task cannot be snoozed.");
        }
    }

    /**
     * Task position out of bounds exception handler.
     *
     * @author lauralee
     */
    public static class TaskPosException extends DukeException {
        /**
         * Constructs a Task Position out of bounds exception.
         */
        public TaskPosException() {
            super("OOPS!! There is no task at the specified task position.");
        }
    }
}

package duke.Exception;

public class DukeException {
    String message;
    /**
     * Constructor for DukeException
     * @param message
     */
    public DukeException(String message) {
        this.message = message;
    }

    public String throwDukeException() {
        return this.message;
    }
    /**
     * Prints error message for invalid input from user.
     */
    public static class InvalidInputException extends DukeException {
        public InvalidInputException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Prints error message for To-do command without description.
     */
    public static class EmptyTodoException extends DukeException {
        public EmptyTodoException() {
            super("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Prints error message for Deadline command without description.
     */
    public static class EmptyDeadlineException extends DukeException {
        public EmptyDeadlineException() {
            super("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    /**
     * Prints error message for Deadline command without time.
     */
    public static class DeadlineWithoutByException extends DukeException {
        public DeadlineWithoutByException() {
            super("☹ OOPS!!! A deadline must have a /by " );
        }
    }

    /**
     * Prints error message for Event command without description.
     */
    public static class EmptyEventException extends DukeException {
        public EmptyEventException() {
            super("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    /**
     * Prints error message for Event command without date.
     */
    public static class EventWithoutAtException extends DukeException {
        public EventWithoutAtException() {
            super("☹ OOPS!!! An event must have a /at " );
        }
    }

    /**
     * Prints error message for mark command without index of the task.
     */
    public static class EmptyMarkingException extends DukeException {
        public EmptyMarkingException() {
            super("☹ OOPS!!! Mark/Unmark must follow by a index of selected item.");
        }
    }

    /**
     * Prints error message for delete commanf without index of the task.
     */
    public static class EmptyDeleteException extends DukeException {
        public EmptyDeleteException() {
            super("☹ OOPS!!! Delete must follow by a index of selected item.");
        }
    }
}
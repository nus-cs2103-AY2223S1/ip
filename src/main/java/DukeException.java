public class DukeException {
    public DukeException(String message) {
        System.out.println(message);
    }

    public static class InvalidInputException extends DukeException {
        public InvalidInputException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static class EmptyTodoException extends DukeException {
        public EmptyTodoException() {
            super("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static class EmptyDeadlineException extends DukeException {
        public EmptyDeadlineException() {
            super("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    public static class DeadlineWithoutByException extends DukeException {
        public DeadlineWithoutByException() {
            super("☹ OOPS!!! A deadline must have a /by " );
        }
    }

    public static class EmptyEventException extends DukeException {
        public EmptyEventException() {
            super("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    public static class EventWithoutAtException extends DukeException {
        public EventWithoutAtException() {
            super("☹ OOPS!!! An event must have a /at " );
        }
    }

    public static class EmptyMarkingException extends DukeException {
        public EmptyMarkingException() {
            super("☹ OOPS!!! Mark/Unmark must follow by a index of selected item.");
        }
    }

    public static class EmptyDeleteException extends DukeException {
        public EmptyDeleteException() {
            super("☹ OOPS!!! Delete must follow by a index of selected item.");
        }
    }
}
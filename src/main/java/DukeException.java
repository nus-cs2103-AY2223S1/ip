public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }

    public static class InvalidActionException extends DukeException {
        public InvalidActionException(String action) {
            super("I'm sorry, but I don't know what [" + action + "] means :-(");
        }
    }

    public static class NoArgumentException extends DukeException {
        public NoArgumentException(Action action) {
            super("The description of a [" + Action.getString(action) + "] cannot be empty." +
                    "\nThe format of [" + Action.getString(action)+ "] should be '" + Action.getFormat(action)+ "'");
        }
    }

    public static class InvalidArgumentException extends DukeException {
        public InvalidArgumentException(Action action, String message) {
            super("The description of a [" + Action.getString(action) + "] is incorrect.\n" + message +
                    "\nThe format of [" + Action.getString(action)+ "] should be '" + Action.getFormat(action)+ "'");
        }
    }

    public static class TaskNotFoundException extends DukeException {
        public TaskNotFoundException(int idTask) {
            super("Task No.: " + String.valueOf(idTask) + " is not found in the task list. \n" +
                    "Try command [list] to show the tasks you may choose from.");
        }
    }
}

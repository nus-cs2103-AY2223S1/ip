import java.nio.file.Path;

public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }

    public static class CompileException extends DukeException {
        public CompileException(String msg) {
            super(" I'm sorry that your entry does not compile, "
                    + "please check with following information:\n"
                    + msg);
        }
    }

    public static class RuntimeException extends DukeException {
        public RuntimeException(String msg) {
            super(" I'm sorry that an error occurs when executing your command, "
                    + "please check with following information:\n" + msg);
        }
    }

    public static class InvalidActionException extends DukeException.CompileException {
        public InvalidActionException(String action) {
            super("I don't know what [" + action + "] means :-(");
        }
    }

    public static class NoArgumentException extends DukeException.CompileException {
        public NoArgumentException(Action action) {
            super("The description of a [" + Action.getString(action) + "] cannot be empty."
                    + "\nThe format of [" + Action.getString(action)+ "] should be '" + Action.getFormat(action)+ "'");
        }
    }

    public static class InvalidArgumentException extends DukeException.CompileException {
        public InvalidArgumentException(Action action, String message) {
            super("The description of a [" + Action.getString(action) + "] is incorrect.\n"
                    + message
                    + "\nThe format of [" + Action.getString(action)+ "] should be '" + Action.getFormat(action) + "'"
                    + "\nAttribute Separator: '" + Parser.getAttributeSeparator() + "', is not allowed.");
        }
    }

    public static class ReadAttributeException extends DukeException.RuntimeException {
        public ReadAttributeException(String className, String formattedString, String message) {
            super("When reading from '" + formattedString + "' in class " + className + ":\n" + message);
        }
    }

    public static class ReadFileException extends DukeException.RuntimeException {
        public ReadFileException(Path path, String message) {
            super("When visiting '" + path.toString() + "'." + ":\n" + message);
        }
    }

    public static class WriteFileException extends DukeException.RuntimeException {
        public WriteFileException(Path path, String message) {
            super("When writing '" + path.toString() + "'." + ":\n" + message);
        }
    }

    public static class TaskNotFoundException extends DukeException.RuntimeException {
        public TaskNotFoundException(int idTask) {
            super("Task No.: " + String.valueOf(idTask) + " is not found in the task list. \n" +
                    "Try command [list] to show the tasks you may choose from.");
        }
    }
}

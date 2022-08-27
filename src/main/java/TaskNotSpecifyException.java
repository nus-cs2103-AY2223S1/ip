public class TaskNotSpecifyException extends DukeException {
    public TaskNotSpecifyException(String command) {
        super("We expect some task to be specified after " + command + " command!\nIt should not be empty!");
    }
}

public class MissingInfoException extends DukeException {
    public MissingInfoException(boolean isToDo) {
        super("Please provide a task description" + (isToDo ? "" : " and a date / time") + "!");
    }
}

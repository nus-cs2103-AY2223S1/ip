public class ToDo extends Task {
    public ToDo(boolean isDone, String[] args) throws DukeException {
        super(args, "todo", isDone, args);
    }
}

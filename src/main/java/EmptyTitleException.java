public class EmptyTitleException extends InvalidCommandException {
    public EmptyTitleException() {
        super("Cannot create a task with an empty title!");
    }
}


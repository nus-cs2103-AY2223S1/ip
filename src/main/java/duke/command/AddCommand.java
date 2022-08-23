package duke.command;

public abstract class AddCommand extends Command {
    String msg;

    protected AddCommand(Action action, String message) {
        super(action);
        this.msg = message;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}

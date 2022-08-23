package duke.command;

public class EmptyCommand implements ICommand {
    @Override
    public void execute() {
        return;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

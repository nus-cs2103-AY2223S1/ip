package main.java;

public class ExitCommand extends Command {
    private boolean isExit = false;

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
    }
}

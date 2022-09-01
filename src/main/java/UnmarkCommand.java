public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int command) {
        index = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmark(index);
            storage.unmark(index);
            ui.showMessage("Got it! Task " + index + " has not yet been completed:\n  " + tasks.showTask(index));
        } catch (IndexOutOfBoundsException e) {
            ui.showError("You've given me an invalid task to unmark!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

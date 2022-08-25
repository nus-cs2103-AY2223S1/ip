public class MarkCommand extends Command {
    int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index >= tasks.size()) {
            throw new DukeException("It seems that there is no corresponding task.");
        }
        tasks.get(index).setStatus(true);
        storage.writeFile(tasks, ui);
        ui.markMessage(tasks, index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index >= tasks.size()) {
            throw new DukeException("It seems that there is no corresponding task.");
        }
        ui.deleteMessage(tasks, index, false);
        tasks.remove(index);
        storage.writeFile(tasks, ui);
        ui.deleteMessage(tasks, index, true);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

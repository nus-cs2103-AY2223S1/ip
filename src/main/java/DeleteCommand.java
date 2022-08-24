public class DeleteCommand extends Command {
    private int num;
    public DeleteCommand(int i) {
        this.num = i;
    }
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task deletedTask = tasks.delete(num - 1);
            storage.overwrite();
            ui.sayDeleted(deletedTask, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("How to delete something that is not inside??");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

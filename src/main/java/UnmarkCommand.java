public class UnmarkCommand extends IndexedCommand {
    UnmarkCommand(int index) {
        super(index);
    }

    @Override
    void execute() throws DukeException {
        Task task = taskList.unmarkTask(super.index);
        Command.storage.updateLine(index, task.toStorageFormat());
        Command.ui.displayUnmarkTaskMessage(task);
    }
}

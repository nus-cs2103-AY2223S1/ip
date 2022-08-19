public class DeleteCommand extends IndexedCommand {
    DeleteCommand(int index) {
        super(index);
    }

    @Override
    void execute() throws DukeException {
        Task task = Command.taskList.delete(super.index);
        Command.storage.save(task);
        Command.ui.displayDeleteTaskMessage(task, Command.taskList.size());
    }
}

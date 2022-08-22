public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.get(index);
        taskList.delete(index);
        ui.showDelete(taskList, deletedTask);
        new SaveCommand().execute(taskList, ui, storage);
    }
}

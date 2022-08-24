public class ByeCommand extends Command {
    public ByeCommand () {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.saveTasksInStorage(taskList.getList());
    }

    @Override
    public boolean isExit(){
        return true;
    }
}

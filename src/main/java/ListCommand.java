public class ListCommand extends Command {

    public ListCommand(Storage storage, Ui ui, TaskList taskList) {
        super(storage, ui, taskList);
    }

    @Override
    public void execute() {
        taskList.displayList();
    }
}

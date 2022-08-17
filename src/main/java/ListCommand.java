public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.printList(taskList.toString());
    }
}

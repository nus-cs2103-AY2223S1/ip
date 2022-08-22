/**
 * The ListCommand class deals with printing the list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

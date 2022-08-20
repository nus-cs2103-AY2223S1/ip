public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        if (taskList.size() > 0) {
            ui.showOutput("Here are the tasks in your list:\n");
            ui.showOutput(taskList.toString());
        } else {
            ui.showOutput("Your list is empty :(\n");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package duke;

public class ListCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showTaskList(taskList);
    }

    public boolean isExit() {
        return false;
    }
}

package duke;

public class ExitCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.save(taskList);
        ui.showExit();
    }

    public boolean isExit() {
        return true;
    }
}

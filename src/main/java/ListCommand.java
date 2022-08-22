public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        ui.showListOut(taskList);
    }
}

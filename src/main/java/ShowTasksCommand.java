public class ShowTasksCommand extends Command {

    public void execute(TasksController controller, Ui ui, Storage storage) {
        ui.display(controller.toString(), true, false, false, false);
    }
}

public class ExitCommand extends Command {

    public void execute(TasksController controller, Ui ui, Storage storage) {
        ui.sayBye();
        storage.save(controller.getTasks());
        System.exit(0);
    }
}

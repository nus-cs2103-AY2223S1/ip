public class ListCommand extends Command {
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks);
    }
}

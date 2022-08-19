public class AddCommand extends Command {

    private Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks);
        tasks.updateStorage(storage);
    }
}

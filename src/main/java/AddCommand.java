public class AddCommand extends Command {
    private Task task;
    AddCommand (Task task) {
        this.task = task;
    }
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        ui.addTaskMessage(task, tasks);
        storage.save(tasks.toString());
    }
}

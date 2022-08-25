public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.writeFile(tasks, ui);
        ui.addMessage(tasks, task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

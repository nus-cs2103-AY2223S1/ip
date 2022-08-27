public class AddCommand extends Command {
    AddCommand(Task task) {
        super(task);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task, ui);
        storage.saveToFile(tasks);
    }
}

public class DeleteCommand extends Command {
    DeleteCommand(int index) {
        super(index);
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(index, ui);
        storage.saveToFile(tasks);
    }
}

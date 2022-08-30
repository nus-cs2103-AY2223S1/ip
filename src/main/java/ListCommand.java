public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.nTasks() == 0) {
            ui.print("\tNo tasks added yet.");
        } else {
            ui.print("\tHere are the tasks in your list:");
            ui.print(tasks.toString());
        }
    }
}

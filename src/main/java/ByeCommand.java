/**
 * Terminates the chatbot program after execution, and saves the current tasks to some local storage
 */
public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        storage.saveTasks(tasks);
    }

    public boolean isExit() {
        return true;
    }
}

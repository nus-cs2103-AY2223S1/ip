package duke.commands;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays a list of all tasks containing the provided keyword.
     *
     * @param tasks The <code>TaskList</code> object containing all stored tasks.
     * @param ui The <code>Ui</code> object
     * @param storage The database object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(this.keyword)) {
                stringBuilder.append(i + 1).append(". ").append(task.toString()).append('\n');
            }
        }

        ui.sendMessage(stringBuilder.toString());
    }
}

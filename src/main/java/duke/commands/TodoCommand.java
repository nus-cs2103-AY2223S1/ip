package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {

    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a new Todo task and add it into the <Code>TaskList</Code>.
     *
     * @param tasks The <code>TaskList</code> object containing all stored tasks.
     * @param ui The <code>Ui</code> object
     * @param storage The database object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Todo newTask = new Todo(this.description);
            tasks.addTask(newTask);
            ui.sendMessage(newTask.getAddMessage(tasks.getSize()));
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        tasks.updateStorage();
    }
}

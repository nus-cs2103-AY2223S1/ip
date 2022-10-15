package duke.commands;

import java.time.LocalDate;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {

    private final String description;
    private final LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Creates a new Deadline task and add it into the <Code>TaskList</Code>.
     *
     * @param tasks The <code>TaskList</code> object containing all stored tasks.
     * @param ui The <code>Ui</code> object
     * @param storage The database object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = new Deadline(description, by);
            tasks.addTask(newTask);
            ui.sendMessage(newTask.getAddMessage(tasks.getSize()));
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        tasks.updateStorage();
    }
}

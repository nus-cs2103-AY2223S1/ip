package yilia.command;

import yilia.Storage;
import yilia.Ui;
import yilia.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }
    /**
     * Executes the exit command.
     *
     * @param tasks The tasks.
     * @param ui The use interface.
     * @param storage The local storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
            ui.showBye();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}

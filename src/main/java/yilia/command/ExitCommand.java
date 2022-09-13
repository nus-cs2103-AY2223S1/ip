package yilia.command;

import java.io.IOException;

import yilia.util.Storage;
import yilia.util.Ui;
import yilia.task.TaskList;

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
     * @return The message after executing.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
            return ui.showBye();
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }
    }
}

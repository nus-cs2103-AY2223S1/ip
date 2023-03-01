package commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command for exiting the program.
 */
public class ByeCommand extends Command {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public ByeCommand(TaskList tasks, Ui ui, Storage storage) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
    }


    /**
     * Executes the command, and returns a String
     * describing the execution of this Command.
     * @return A String describing the Tasks that were saved.
     */
    public String execute() {
        try {
            this.storage.save(tasks);
        } catch (IOException e) {
            return "Sorry, I failed to save your file due to: " + ui.showError(e);
        }
        return ui.showBye();
    }
}

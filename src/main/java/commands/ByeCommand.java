package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class ByeCommand extends Command {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public ByeCommand(TaskList tasks, Ui ui, Storage storage) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
    }


    public String execute() {
        try {
            this.storage.save(tasks);
        } catch (IOException e) {
            return "Sorry, I failed to save your file due to: " + ui.showError(e);
        }
        return ui.showBye();
    }
}

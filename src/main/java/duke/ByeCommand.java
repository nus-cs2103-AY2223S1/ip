package duke;

public class ByeCommand extends Command {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public ByeCommand(Storage storage, Ui ui, TaskList tasks) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
    }
    @Override
    public String execute() {
        try {
            this.storage.saveTasks(this.tasks);
            return this.ui.showGoodbye();
        } catch (DukeException e) {
            return this.ui.showError(e);
        }
    }
}

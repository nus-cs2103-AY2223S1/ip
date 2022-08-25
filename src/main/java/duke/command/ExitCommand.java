package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.store(tasks);
        } catch (Exception e) {
            System.out.print("");
        } finally {
            ui.showBye();
        }
    }
}

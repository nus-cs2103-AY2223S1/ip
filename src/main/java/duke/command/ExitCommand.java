package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TasksList;

public class ExitCommand extends Command {
    private TasksList tasksList;
    private Storage storage;
    private Ui ui;

    public ExitCommand(Ui ui, Storage storage, TasksList tasksList) {
        this.ui = ui;
        this.storage = storage;
        this.tasksList = tasksList;
    }

    @Override
    public void execute() throws DukeException {
        this.ui.endSession(this.storage, this.tasksList);
    }

    public static boolean isCommand(String s) {
        return s.equals("bye");
    }
}

package duke.command;

//import util
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

//import exception
import duke.exception.DukeException;

public abstract class Command {
    boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }

    protected void displayCommand(Ui ui, String header, Object body, String footer) {
        ui.show(header);
        ui.show(body);
        ui.show(footer);
    }
}

package command;

import java.io.IOException;

import wagwan.Storage;
import wagwan.TaskList;
import wagwan.WagwanException;
import wagwan.WagwanUi;

/**
* Command that exits the program when executed.
*/
public class ExitCommand extends Command {

    private boolean isExit;

    /**
     * Constructor for an exit command.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(TaskList tasks, WagwanUi ui, Storage storage) throws WagwanException {
        try {
            storage.save();
        } catch (IOException e1) {
            throw new WagwanException(e1.getMessage());
        } catch (WagwanException e2) {
            return e2.toString();
        }
        return ui.endMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "this is an exit command";
    }
}

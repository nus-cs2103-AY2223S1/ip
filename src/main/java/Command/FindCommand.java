package Command;
import Duke.DukeException;
import Duke.DukeUi;
import Duke.Storage;
import Duke.TaskList;

/**
* Command that returns the list of tasks that contains the specified keyword when executed.
*/
public class FindCommand extends Command {

    boolean exit;
    private String userAction;

    public FindCommand(String userAction) {
        this.exit = false;
        this.userAction = userAction;
    }

    @Override
    public void execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        String findMessage = tasks.find(userAction);
        ui.sendMessage(findMessage);
    }

    @Override
    public String toString() {
        return "this is a find command : find " + userAction;
    }
}

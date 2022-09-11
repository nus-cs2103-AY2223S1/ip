package Command;
import Duke.DukeException;
import Duke.DukeUi;
import Duke.Storage;
import Duke.TaskList;

/**
* Command that returns the list of tasks that contains the specified keyword when executed.
*/
public class FindCommand extends Command {

    boolean isExit;
    private String userAction;

    public FindCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        return tasks.find(userAction);
    }

    @Override
    public String toString() {
        return "this is a find command : find " + userAction;
    }
}

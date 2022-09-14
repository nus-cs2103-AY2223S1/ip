package Command;
import Duke.WagwanException;
import Duke.WagwanUi;
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
    public String execute(TaskList tasks, WagwanUi ui, Storage storage) throws WagwanException {
        return tasks.find(userAction);
    }

    @Override
    public String toString() {
        return "this is a find command : find " + userAction;
    }
}

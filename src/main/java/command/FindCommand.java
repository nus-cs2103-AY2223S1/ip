package command;

import wagwan.Storage;
import wagwan.TaskList;
import wagwan.WagwanException;
import wagwan.WagwanUi;

/**
* Command that returns the list of tasks that contains the specified keyword when executed.
*/
public class FindCommand extends Command {

    private boolean isExit;
    private String userAction;

    /**
     * Constructor for a find command.
     * @param userAction the keyword used to search for matching task descriptions.
     */
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

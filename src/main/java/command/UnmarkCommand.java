package command;

import task.Task;

/**
 *  A class which encapsulates the unmark command of Duke.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class UnmarkCommand extends Command {
    Task currTask;

    public UnmarkCommand(Task taskToUnmark) {
        this.currTask = taskToUnmark;
    }

    /**
     * Executes the unmark command and shows the user the task unmarked.
     * @return Duke's response to be passed into the Dialog Box.
     */
    @Override
    public String execute() {
        currTask.setUndone();
        String result = "Oh man! I've marked this task as undone:\n";
        result += currTask;
        return result;
    }

}

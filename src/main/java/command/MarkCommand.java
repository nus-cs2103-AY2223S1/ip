package command;

import task.Task;

/**
 *  A class which encapsulates the mark command of Duke.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class MarkCommand extends Command {
    Task currTask;

    public MarkCommand(Task taskToMark) {
        this.currTask = taskToMark;
    }

    /**
     * Executes the mark command and shows the user the task marked.
     * @return Duke's response which is the task marked, to be passed into the Dialog Box.
     */
    @Override
    public String execute() {
        currTask.setDone();
        String result = "Let's go! I've marked this task as done:\n";
        result += currTask;
        return result;
    }
}

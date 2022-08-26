package duke.command;

import duke.storage.TaskRecords;
import duke.task.Task;
import duke.ui.BotUI;

/**
 * Represents a find command of task. A <code>FindCommand</code> object stores
 * the details of the task as a String type Integer. eg. "1"
 *
 */

public class FindCommand extends Command {
    private final String details;

    /**
     * Constructs DeleteCommand object
     *
     * @param command command of the user input
     * @param details details of the user input as String type Integer
     */
    public FindCommand(String command, String details) {
        super(command);
        this.details = details;
    }

    /**
     * Finds task's detail consisting the keyword from the FindCommand detail attribute.
     *
     * @param taskList stores the list of tasks
     * @param ui Object that responsible in returning necessary formatted String
     *           to print on the user interface
     */
    @Override
    public void execute(TaskRecords taskList, BotUI ui) {
        TaskRecords foundList = new TaskRecords();
        boolean found = false;
        for (Task t : taskList.getList()) {
            if (t.getDetail().contains(this.details)) {
                foundList.addProcess(t);
                found = true;
            }
        }
        if (found) {
            ui.taskFound(foundList);
        } else {
            ui.taskNotFound();
        }

    }
    /**
     * Returns the true/false of the command exit status that
     * will cause duke stop running
     *
     * @return the true/false of the command exit status
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

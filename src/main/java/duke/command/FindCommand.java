package duke.command;

import static duke.common.Messages.MESSAGE_FOUND_TASK;

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
     * Constructor of DeleteCommand
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
        StringBuilder foundTasks = new StringBuilder(MESSAGE_FOUND_TASK + "\n");
        boolean found = false;
        for (Task t : taskList.getList()) {
            if (t.getDetail().contains(this.details)) {
                foundTasks.append(t).append("\n");
                found = true;
            }
        }
        if (found) {
            System.out.println(ui.botSpeak(foundTasks.toString()));
        } else {
            System.out.println(ui.botSpeak("Nothing found!"));
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}

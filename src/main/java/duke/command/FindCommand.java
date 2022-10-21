package duke.command;

import duke.common.AnomaliesManager;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.BotUI;

/**
 * Represents a find command of task. A <code>FindCommand</code> object stores
 * the details of the task as a String type Integer. eg. "1".
 */
public class FindCommand extends Command {
    private final String detail;

    /**
     * Constructs FindCommand object.
     *
     * @param command Command of the user input.
     * @param detail Detail of the user input as String type Integer.
     */
    public FindCommand(String command, String detail) {
        super(command);
        this.detail = detail;
    }

    /**
     * Finds and returns task's detail consisting the keyword from the FindCommand detail attribute.
     *
     * @param taskList Stores the list of tasks.
     * @param ui Object that responsible in returning necessary formatted String
     *           to print on the user interface.
     * @param anomaliesManager Responsible to handle anomaly and store command with anomalies.
     * @return String of suitable response according to the user input through BotUI object.
     */
    @Override
    public String execute(TaskList taskList, BotUI ui, AnomaliesManager anomaliesManager) {
        TaskList foundList = new TaskList();
        boolean hasFound = false;
        for (Task t : taskList.getList()) {
            if (t.getDetail().contains(this.detail)) {
                foundList.addTask(t);
                hasFound = true;
            }
        }
        if (hasFound) {
            return ui.taskFound(foundList);
        } else {
            return ui.taskNotFound();
        }

    }
    /**
     * Returns the true/false of the command exit status that
     * will allow user to stop using Duke.
     *
     * @return The true/false of the command exit status.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the same object.
     * This is because this method is temporary used for AddCommand only.
     * Provides flexibility to FindCommand class for future anomaly checking.
     *
     * @return The same object.
     * @see AddCommand class.
     */
    @Override
    public FindCommand resolveAnomaly() {
        return this;
    }
}

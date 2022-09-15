package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

/**
 * StatisticsCommand allows users to view task insights.
 */
public class StatisticsCommand extends Command {

    /**
     * Constructor for StatisticsCommand
     */
    public StatisticsCommand() {
        super();
    }

    /**
     * Executes StatisticsCommand by iterating through the list and numerating completed and incomplete tasks.
     *
     * @param taskList  Task list containing tasks to be examined.
     * @param ui Ui to retrieve return string from.
     * @param s Storage containing the list of tasks or where tasks should be saved.
     * @return String containing task insights.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage s) {
        int completeTasks = 0;
        int incompleteTasks = 0;
        for (int i = 0; i < taskList.getSize(); i++) {
            boolean isDone = taskList.retrieveTask(i).isDone();
            if (isDone) {
                completeTasks += 1;
            } else {
                incompleteTasks += 1;
            }
        }
        return completeTasks + " tasks have been completed and " + incompleteTasks
                + " have not!";
    }
}

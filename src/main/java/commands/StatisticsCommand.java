package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

public class StatisticsCommand extends Command {

    public StatisticsCommand() {
        super();
    }

    public String execute(TaskList taskList, Ui ui, Storage s) {
        int completeTasks = 0;
        int incompleteTasks = 0;
        for (int i = 0; i < taskList.getSize(); i++) {
            boolean isDone = taskList.retrieveTask(i).getStatusIcon().equals("X");
            if (isDone) {
                completeTasks += 1;
            }
            incompleteTasks += 1;
        }
        return "You have completed " + completeTasks + " tasks and have " + incompleteTasks
                + " tasks left incomplete.";
    }
}

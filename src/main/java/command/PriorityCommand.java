package command;

import duke.TaskList;
import duke.Ui;
import task.Task;
import java.text.ParseException;

/**
 * An abstract class that represents PriorityCommand which extends Command
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.2
 * @since   2022-9-15
 */
public class PriorityCommand extends Command{

    private TaskList taskList;
    int index;
    private String priority;
    private Ui ui;

    /**
     * Constructor for PriorityCommand Object
     */
    public PriorityCommand(TaskList taskList, int index, String priority) {
        this.taskList = taskList;
        this.index = index;
        this.priority = priority;
        this.ui = new Ui();
    }

    /**
     * Returns a string of the executed PriorityCommand
     * @return a string after the execution of PriorityCommand
     */
    @Override
    public String execute() throws ParseException {
        if (priority.equalsIgnoreCase("high")) {
            Task task = taskList.get(index);
            task.setPriority("[H]");
            String taskName = task.toString();
            taskList.set(index, task);
            return ui.highPriorityMessage(taskName);

        } else if (priority.equalsIgnoreCase("medium")) {
            Task task = taskList.get(index);
            task.setPriority("[M]");
            String taskName = task.toString();
            taskList.set(index, task);
            return ui.mediumPriorityMessage(taskName);

        } else if (priority.equalsIgnoreCase("low")) {
            Task task = taskList.get(index);
            task.setPriority("[L]");
            String taskName = task.toString();
            taskList.set(index, task);
            return ui.lowPriorityMessage(taskName);

        } else {
            return ui.incorrectPriorityMessage();
        }
    }
}

package command;

import duke.TaskList;
import duke.Ui;
import task.Events;
import task.Task;
import task.ToDos;

import java.text.ParseException;

public class PriorityCommand extends Command{

    private TaskList taskList;
    int index;
    private String priority;
    private Ui ui;

    public PriorityCommand(TaskList taskList, int index, String priority) {
        this.taskList = taskList;
        this.index = index;
        this.priority = priority;
        this.ui = new Ui();
    }

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

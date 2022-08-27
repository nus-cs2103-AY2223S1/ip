package command;

import task.Task;
import tasklist.TaskList;
import util.Storage;
import util.Ui;

/**
 * Represents a command to be executed that adds a task to the internal duke list.
 *
 * @author Bryan Lim Jing Xiang
 */
public class AddTaskCommand extends Command {
    private final Task taskItem;

    public AddTaskCommand(Task taskItem) {
        this.taskItem = taskItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        String task = "  " + list.addTask(this.taskItem);
        String startLine = "Got it. I've added this task:";
        String endLine = String.format(
                "Now you have %d tasks in the list.",
                list.getTaskCount());
        System.out.println(Ui.formatLinesIntoParagraph(
                startLine,
                task,
                endLine
        ));
    }
}

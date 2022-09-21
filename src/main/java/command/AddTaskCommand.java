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
        Task task = list.addTask(this.taskItem);
        if (task == null) {
            setOutputMessage(Ui.formatLinesIntoParagraph(
                    "This task has already been added to the list previously."
            ));
            return;
        }
        assert task != null;
        String taskDescription = " " + task.toString();
        String startLine = "Got it. I've added this task:";
        String endLine = String.format(
                "Now you have %d tasks in the list.",
                list.getTaskCount());
        setOutputMessage(Ui.formatLinesIntoParagraph(
                startLine,
                taskDescription,
                endLine
        ));
    }
}

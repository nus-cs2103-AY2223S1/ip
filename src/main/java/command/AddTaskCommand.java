package command;

import task.Task;
import tasklist.TaskList;
import util.Storage;
import util.Ui;

public class AddTaskCommand extends Command {
    private final Task taskItem;

    public AddTaskCommand(Task taskItem) {
        this.taskItem = taskItem;
    }

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
